param(
  [Parameter(Mandatory = $true)]
  [string]$SourceSql,
  [Parameter(Mandatory = $true)]
  [string]$ActualsSql,
  [Parameter(Mandatory = $true)]
  [string]$OutputSql,
  [switch]$NoOnConflict
)

$ErrorActionPreference = 'Stop'

function Split-SqlValues([string]$text) {
  $values = New-Object System.Collections.Generic.List[string]
  $builder = New-Object System.Text.StringBuilder
  $inQuote = $false
  for ($index = 0; $index -lt $text.Length; $index++) {
    $char = $text[$index]
    if ($char -eq "'") {
      if ($inQuote -and $index + 1 -lt $text.Length -and $text[$index + 1] -eq "'") {
        [void]$builder.Append("''")
        $index++
        continue
      }
      $inQuote = -not $inQuote
      [void]$builder.Append($char)
      continue
    }

    if ($char -eq ',' -and -not $inQuote) {
      $values.Add($builder.ToString().Trim())
      $builder.Clear() | Out-Null
      continue
    }

    [void]$builder.Append($char)
  }

  $values.Add($builder.ToString().Trim())
  return $values
}

function Unquote([string]$value) {
  if ($null -eq $value) { return $null }
  $trimmed = $value.Trim()
  if ($trimmed -match "^'.*'$") {
    return ($trimmed.Substring(1, $trimmed.Length - 2) -replace "''", "'")
  }
  if ($trimmed -match '^(?i)null$') {
    return $null
  }
  return $trimmed
}

function Parse-SourceRows([string]$filePath) {
  $rows = @()
  $valuesRegex = [regex]'VALUES\((.*)\);'

  foreach ($line in Get-Content -LiteralPath $filePath) {
    $match = $valuesRegex.Match($line)
    if (-not $match.Success) { continue }

    $parts = Split-SqlValues $match.Groups[1].Value
    if ($parts.Count -lt 2) { continue }

    $rows += [pscustomobject]@{
      unitatid = [int](Unquote $parts[0])
      codi = Unquote $parts[1]
      raw = $line
    }
  }

  return $rows
}

function Parse-ActualRows([string]$filePath) {
  $rows = @()
  $valuesRegex = [regex]'VALUES\((.*)\);'

  foreach ($line in Get-Content -LiteralPath $filePath) {
    $match = $valuesRegex.Match($line)
    if (-not $match.Success) { continue }

    $parts = Split-SqlValues $match.Groups[1].Value
    if ($parts.Count -lt 4) { continue }

    $rows += [pscustomobject]@{
      unitatid = [int](Unquote $parts[0])
      codi = Unquote $parts[3]
      raw = $line
    }
  }

  return $rows
}

$sourceRows = Parse-SourceRows -filePath $SourceSql
$actualRows = Parse-ActualRows -filePath $ActualsSql

$actualByCodi = @{}
$duplicateCodisInActuals = New-Object System.Collections.Generic.HashSet[string]
foreach ($row in $actualRows) {
  if ($actualByCodi.ContainsKey($row.codi)) {
    [void]$duplicateCodisInActuals.Add($row.codi)
  } else {
    $actualByCodi[$row.codi] = $row
  }
}

$sourceByCodi = @{}
foreach ($row in $sourceRows) {
  if (-not $sourceByCodi.ContainsKey($row.codi)) {
    $sourceByCodi[$row.codi] = $row
  }
}

$linesOut = New-Object System.Collections.Generic.List[string]
$idsChanged = 0
$codisMissingInActuals = New-Object System.Collections.Generic.List[string]
$insertRegex = [regex]"VALUES\((\d+),\s*'((?:''|[^'])*)'"

foreach ($line in Get-Content -LiteralPath $SourceSql) {
  $match = $insertRegex.Match($line)
  if (-not $match.Success) {
    $linesOut.Add($line)
    continue
  }

  $oldId = [int]$match.Groups[1].Value
  $codi = $match.Groups[2].Value -replace "''", "'"
  $newLine = $line

  if ($actualByCodi.ContainsKey($codi)) {
    $newId = [int]$actualByCodi[$codi].unitatid
    if ($newId -ne $oldId) { $idsChanged++ }
    $newLine = [regex]::Replace($newLine, 'VALUES\(\d+,', "VALUES($newId,")
  } else {
    $codisMissingInActuals.Add($codi)
  }

  if (-not $NoOnConflict) {
    $newLine = [regex]::Replace($newLine, ';\s*$', ' ON CONFLICT (unitatid) DO NOTHING;')
  }

  $linesOut.Add($newLine)
}

$outputDirectory = Split-Path -Parent $OutputSql
if (-not [string]::IsNullOrWhiteSpace($outputDirectory) -and -not (Test-Path -LiteralPath $outputDirectory)) {
  New-Item -ItemType Directory -Path $outputDirectory -Force | Out-Null
}

Set-Content -LiteralPath $OutputSql -Value $linesOut -Encoding UTF8

$actualsNotInSource = @($actualByCodi.Keys | Where-Object { -not $sourceByCodi.ContainsKey($_) } | Sort-Object)

[pscustomobject]@{
  outputFile = $OutputSql
  sourceRows = $sourceRows.Count
  actualRows = $actualRows.Count
  idsChanged = $idsChanged
  missingCodisInActuals = @($codisMissingInActuals | Sort-Object -Unique)
  missingCodisInActualsCount = @($codisMissingInActuals | Sort-Object -Unique).Count
  actualsNotInSource = $actualsNotInSource
  actualsNotInSourceCount = $actualsNotInSource.Count
  duplicateCodisInActuals = @($duplicateCodisInActuals)
} | ConvertTo-Json -Depth 6

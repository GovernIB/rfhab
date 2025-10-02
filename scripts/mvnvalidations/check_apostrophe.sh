#!/bin/bash
if grep -q "'" "$1"; then
  echo
  echo "==================== ERROR ===================="
  echo "ERROR: S'ha trobat un apòstrof (') en el fitxer:"
  echo "  $1"
  echo "==============================================="
  exit 1
fi
exit 0
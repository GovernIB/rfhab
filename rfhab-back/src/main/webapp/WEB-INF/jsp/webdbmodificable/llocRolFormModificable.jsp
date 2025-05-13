<script type="text/javascript">

    document.addEventListener("DOMContentLoaded", function(event) {
        
        let select = document.getElementById("llocRol_rolID");
        
        let options = select.options;
    
        for	(let i = 0; i < options.length; i++) {
                
            // create chekbox
            let checkbox = document.createElement('input');
            checkbox.type = "checkbox";
            checkbox.name = "rolID_" + options[i].value;
            checkbox.value = options[i].value;
            checkbox.id = "rolID_" + options[i].value;
            
            // create label
            let label = document.createElement('label')
            label.htmlFor = "rolID_" + options[i].value;
            label.style.marginLeft = "5px";
            label.appendChild(document.createTextNode(options[i].text));
            
            // append checkbox and label
            select.parentNode.appendChild(checkbox);
            select.parentNode.appendChild(label);
            select.parentNode.appendChild(document.createElement('br'));
        }
        
        select.parentNode.removeChild(select);
    
        <c:if test="${not rolsLloc.isEmpty()}">
            <c:forEach items="${rolsLloc}" var="rolLloc">
                document.getElementById("rolID_${rolLloc.rolID}").checked = true;
            </c:forEach>
        </c:if>
    }); 
    </script>
    
    
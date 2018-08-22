var httpRequest;
(function changePicture()
{
    if (httpRequest.readyState === XMLHttpRequest.DONE)
    		{
    			if (httpRequest.status == 200)
    			{
    			    var bt=document.getElementById('btnsave');
    			    var parent = bt.parentNode;
    			    parent.removeChild(bt);
    			    var remove = document.createElement("button");
    			    remove.type="submit";
                    remove.id="removebtn";
                    remove.innerHTML = "Remove profile picture";
                    remove.addEventListener('click', function()
    			    {
                    	remove.action="<c:url value="/remove-picture"/>";
                    	remove.method="POST";
                    	remove.submit();
                    	var parent = remove.parentNode;
                    	parent.removeChild(remove);
                    	var show = document.createElement("button");
                    	show.type="submit";
                    	show.id="btnsave";
                    	show.innerHTML = "Save";
                    	show.addEventListener('click', changePicture);
                    	parent.appendChild(show);


                    });
                    parent.appendChild(hide);
    			}
    		}
}
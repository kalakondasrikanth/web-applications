/*
var gdpr = document.getElementById("gdprbtn");
gdpr.addEventListener("click", function() 
{
	var mystyle = window.getComputedStyle(gdpr, null);
	var parent = gdpr.parentNode;
	parent.removeChild(gdpr);
	var remove = document.createElement("button");
	var data = document.createElement("button");
	Array.from(mystyle).forEach(key => remove.style.setProperty(key, mystyle.getPropertyValue(key), mystyle.getPropertyPriority(key)));
	Array.from(mystyle).forEach(key => data.style.setProperty(key, mystyle.getPropertyValue(key), mystyle.getPropertyPriority(key)));
	remove.innerHTML = "Delete Profile";
	data.innerHTML = "Info";
	remove.addEventListener("click", function() { alert("For GDPR law you have the right to get all of your data removed"); });
	parent.appendChild(remove);
	parent.appendChild(data);
	data.onclick = function () {
        location.href = "/leoforfriends-1.00/jsp/privacy.jsp";
    };
});*/

var profileimage = document.getElementById("picture");
var imagesrc = document.getElementById("picture").getAttribute("src");
var caption = document.getElementById("caption");

profileimage.addEventListener("mouseover", function()
{
	profileimage.setAttribute("src", "/leoforfriends-1.00/images/tarsio.jpg");
	caption.innerHTML = "A wonderful specimen of Tarsier.";
});

profileimage.addEventListener("mouseout", function()
{
	profileimage.setAttribute("src", imagesrc);
	caption.innerHTML = "Your profile picture.";
});

var httpRequest;
(function showQuestion() 
{
	document.getElementById('questionbtn').addEventListener('click', makeRequest);

	function makeRequest() 
	{
		var user = document.getElementById('user').innerHTML;
		var username= user.substring(11);
		var url = '/leoforfriends-1.00/rest/questions/users/' + username;

		httpRequest = new XMLHttpRequest();

		if (!httpRequest) 
		{
			alert('Giving up :( Cannot create an XMLHTTP instance');
			return false;
		}

		httpRequest.onreadystatechange = alertContents;
		httpRequest.open('GET', url);
		httpRequest.send();
	}

	function alertContents() 
	{
		if (httpRequest.readyState === XMLHttpRequest.DONE) 
		{
			if (httpRequest.status == 200) 
			{
				var bt=document.getElementById('questionbtn');
				var parent = bt.parentNode;
				parent.removeChild(bt);
				var d = document.createElement("div");
				d.id="hidebtn";
				var hide = document.createElement("button");
				hide.type="submit";
				hide.className="btn btn-secondary";
				hide.innerHTML = "Hide my questions";
				hide.style.color = "black";
				d.appendChild(hide);
				parent.appendChild(hide);
				hide.addEventListener('click', function()
				{
					var parent = hide.parentNode;
					parent.removeChild(hide);
					var d = document.createElement("div");
                    d.id="questionbtn";
					var show = document.createElement("button");
					show.type="submit";
					show.className="btn btn-secondary";
					show.innerHTML = "Show all my questions";
					show.style.color = "black";
					show.addEventListener('click', makeRequest);
					d.appendChild(show);
					parent.appendChild(d)

					var div = document.getElementById('results');
					var table=document.getElementById('resTable');
					div.removeChild(table);
					var p=document.createElement('p');
					p.id="resTable";
					div.appendChild(p);
				});


				var div = document.getElementById('results');
				var p=document.getElementById('resTable');
				div.removeChild(p);

				var table = document.createElement('table');
				table.id='resTable';
				table.align="center";
				var thead = document.createElement('thead');
				thead.id="resthead";
				var tr = document.createElement('tr');

				var th = document.createElement('th');
				th.appendChild(document.createTextNode('Title'));
				tr.appendChild(th);

				var th = document.createElement('th');
				th.appendChild(document.createTextNode('Content'));
				tr.appendChild(th);

				thead.appendChild(tr);
				table.appendChild(thead);

				var jsonData = JSON.parse(httpRequest.responseText);
				var resource = jsonData['resource-list'];

				for (var i = 0; i < resource.length; i++) 
				{
					var question = resource[i].question;

					var tbody = document.createElement('tbody');
					var tr = document.createElement('tr');

					var td_title = document.createElement('td');
					var a = document.createElement('a');
					a.setAttribute('href','/leoforfriends-1.00/jsp/question-page-logged.jsp?value='+question['id']);
					a.appendChild(document.createTextNode(question['title']));
					td_title.appendChild(a);
					tr.appendChild(td_title);

					var td_content = document.createElement('td');
					td_content.appendChild(document.createTextNode(question['content']));
					tr.appendChild(td_content);

					tbody.appendChild(tr);
					table.appendChild(tbody);
				}
				
				div.appendChild(table);
			} 
			else 
			{
				alert('There was a problem with the request. Status error code: ' + httpRequest.status);
			}
		}
	}
})();
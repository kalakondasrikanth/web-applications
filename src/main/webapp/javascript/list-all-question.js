var httpRequest;
window.onload=function()
{
	makeRequest();
};
function makeRequest() 
{
	var url = '/leoforfriends-1.00/rest/questions';
	httpRequest = new XMLHttpRequest();

	if (!httpRequest) 
	{
		alert('Giving up :( Cannot create an XMLHTTP instance');
		return false;
	}

	httpRequest.onreadystatechange = alertContents;
	httpRequest.open('GET', url);
	httpRequest.send();
};

function alertContents() 
{
	if (httpRequest.readyState === XMLHttpRequest.DONE) 
	{
		if (httpRequest.status == 200) 
		{
			var div = document.getElementById('results');
			var table = document.createElement('table');
			table.id = "res";
			table.align = "center";
			var thead = document.createElement('thead');
			thead.id = "resthead";
			var tr = document.createElement('tr');

			var th = document.createElement('th');
			th.appendChild(document.createTextNode('Title'));
			tr.appendChild(th);

			var th = document.createElement('th');
			th.appendChild(document.createTextNode('User'));
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
				if (str != "" && str != null)
					a.setAttribute('href','/leoforfriends-1.00/jsp/question-page-logged.jsp?value='+question['id']);
				else
					a.setAttribute('href','/leoforfriends-1.00/jsp/question-page.jsp?value='+question['id']);
				a.appendChild(document.createTextNode(question['title']));
				td_title.appendChild(a);
				tr.appendChild(td_title);

				var td_actor = document.createElement('td');
				td_actor.appendChild(document.createTextNode(question['actor']));
				tr.appendChild(td_actor);

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
};
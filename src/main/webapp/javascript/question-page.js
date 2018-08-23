var httpRequest;
var httpRequest1;
var parameter = location.search;
var temp = parameter.split("=");
var id = unescape(temp[1]);


window.onload=function()
{
    makeRequest();
    makeRequest1();
};

function makeRequest() 
{
    var url = '/leoforfriends-1.00/rest/questions/questionid/'+id;
    httpRequest1 = new XMLHttpRequest();

    if (!httpRequest1) 
    {
        alert('Giving up :( Cannot create an XMLHTTP instance');
        return false;
    }

    httpRequest1.onreadystatechange = alertContents;
    httpRequest1.open('GET', url);
    httpRequest1.send();
};

function alertContents() 
{
	if (httpRequest1.readyState === XMLHttpRequest.DONE) 
	{

		if (httpRequest1.status == 200) 
		{
			var jsonData = JSON.parse(httpRequest1.responseText);
			var resource = jsonData['resource-list'];
			var question = resource[0].question;

            var div = document.getElementById('question');
            var table = document.createElement('table');
            table.id = "res";
            table.align = "center";

            var tbody = document.createElement('tbody');
            var tr = document.createElement('tr');

            var td_title = document.createElement('td');
            td_title.id="title";
            td_title.appendChild(document.createTextNode('Title'));
            tr.appendChild(td_title);

            var td_title_content = document.createElement('td');
            td_title_content.appendChild(document.createTextNode(question['title']));
            tr.appendChild(td_title_content);

            var td_actor = document.createElement('td');
            td_actor.id="title";
            td_actor.appendChild(document.createTextNode('User'));
            tr.appendChild(td_actor);

            var td_actor_content = document.createElement('td');
            td_actor_content.appendChild(document.createTextNode(question['actor']));
            tr.appendChild(td_actor_content);

            tbody.appendChild(tr);
            table.appendChild(tbody);

            div.appendChild(table);

            var table1 = document.createElement('table');
            table1.id = "res1";
            table1.align = "center";

			var tbody1 = document.createElement('tbody');
            var tr1 = document.createElement('tr');

            var td_content_title = document.createElement('td');
            td_content_title.id="title";
            td_content_title.appendChild(document.createTextNode('Content'));
            tr1.appendChild(td_content_title);

            var td_content = document.createElement('td');
            td_content.appendChild(document.createTextNode(question['content']));
            tr1.appendChild(td_content);

            tbody1.appendChild(tr1);
            table1.appendChild(tbody1);

            div.appendChild(table1);
		} 
		else 
		{
			alert('There was a problem with the request. Status error code: ' + httpRequest1.status);
		}
	}
	
};

function makeRequest1() 
{
    var url = '/leoforfriends-1.00/rest/questions/question/'+id;
    httpRequest = new XMLHttpRequest();

    if (!httpRequest) 
    {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }

    httpRequest.onreadystatechange = alertContents1;
    httpRequest.open('GET', url);
    httpRequest.send();
};

function alertContents1() 
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
        th.appendChild(document.createTextNode('Answer'));
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
            var answer = resource[i].answer;

            var tbody = document.createElement('tbody');
            var tr = document.createElement('tr');

            var td_content = document.createElement('td');
            td_content.appendChild(document.createTextNode(answer['content']));
            tr.appendChild(td_content);

            var td_actor = document.createElement('td');
            td_actor.appendChild(document.createTextNode(answer['user']));
            tr.appendChild(td_actor);

            tbody.appendChild(tr);
            table.appendChild(tbody);
        }
        
        div.appendChild(table);
       
        var a = document.createElement('a');
        var linkText = document.createTextNode("Create an answer");
        a.appendChild(linkText);
        a.title = "Create an answer";
        a.href = "/leoforfriends-1.00/jsp/create-answer-form.jsp?value="+id;
        qlink.appendChild(a);
        
      } 
      else 
      {
        alert('There was a problem with the request. Status error code: ' + httpRequest.status);
      }
    }
};
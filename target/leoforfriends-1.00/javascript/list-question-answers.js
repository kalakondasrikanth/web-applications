var httpRequest;
var parameter = location.search;

    var temp = parameter.split("=");
    var id = unescape(temp[1]);
window.onload=function(){
    makeRequest();
    makeRequest1();
};
  function makeRequest() 
  {
    var url = '/leoforfriends-1.00/rest/questions/questionid/'+id;
    
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


  			var jsonData = JSON.parse(httpRequest.responseText);
  			var resource = jsonData['resource-list'];

  				var question = resource[i].question;

  				var div_title=document.getElementById('title');
  				div_title.appendChild(document.createTextNode(question['title']));

          var div_actor = document.getElementById('actor');
          div_actor.appendChild(document.createTextNode(question['actor']));


  				var div_content = document.getElementById('content');
  				div_content.appendChild(document.createTextNode(question['content']));

  		} 
  		else 
  		{
  			alert('There was a problem with the request. Status error code: ' + httpRequest.status);
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
  }

  function alertContents1() 
  {
    if (httpRequest.readyState === XMLHttpRequest.DONE) 
    {

      if (httpRequest.status == 200) 
      {
        var div = document.getElementById('results');


        var table = document.createElement('table');


        var thead = document.createElement('thead');

        var tr = document.createElement('tr');

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Question number'));
        tr.appendChild(th);

        

        var th = document.createElement('th');
        th.appendChild(document.createTextNode('Content'));
        tr.appendChild(th);

            var th = document.createElement('th');
            th.appendChild(document.createTextNode('Actor'));
            tr.appendChild(th);

        thead.appendChild(tr);
        table.appendChild(thead);

        var tbody = document.createElement('tbody');

        var jsonData = JSON.parse(httpRequest.responseText);
        var resource = jsonData['resource-list'];

        for (var i = 0; i < resource.length; i++) {
          var answer = resource[i].answer;

          var tr = document.createElement('tr');

          var td_id = document.createElement('td');
          td_id.appendChild(document.createTextNode(answer['questionid']));
          tr.appendChild(td_id);

          

          var td_content = document.createElement('td');
          td_content.appendChild(document.createTextNode(answer['content']));
          tr.appendChild(td_content);

                var td_actor = document.createElement('td');
                td_actor.appendChild(document.createTextNode(answer['user']));
                tr.appendChild(td_actor);

          tbody.appendChild(tr);
        }

        table.appendChild(tbody);

        div.appendChild(table);
      } 
      else 
      {
        alert('There was a problem with the request. Status error code: ' + httpRequest.status);
      }
    }
    
  };



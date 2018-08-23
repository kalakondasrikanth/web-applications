if (str != "" && str != null)
{
    $("#logprof").html("<i class=\"fa fa-user\"></i> "+ str);
    $("#setValue").attr("value",str);
}

var form  = document.getElementById("form");
var Title = document.getElementById("title");
var qtext = document.getElementById("qtext");

function validateForm() 
{

	var test = Title.value.length > 0 && qtext.value.length > 0;

	var parent = Title.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = qtext.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";

	if (Title.value.length === 0)
	{
		var parent = Title.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		Title.setAttribute("placeholder", "Title: this field is mandatory");
	}

	if (qtext.value.length === 0)
	{
		var parent = qtext.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		qtext.setAttribute("placeholder", "Content: this field is mandatory");
	}

	return test;
}

form.onsubmit = validateForm;
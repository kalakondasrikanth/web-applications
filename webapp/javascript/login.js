var form  = document.getElementById("logform");

var username = document.getElementById("usernamebox");
username.oninput = function(){
	username.setAttribute("placeholder", "Username");
	var parent = username.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
}

var pw = document.getElementById("pwbox");
pw.oninput = function(){
	pw.setAttribute("placeholder", "Password");
	var parent = pw.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
}

function validateForm() 
{

	var test = username.value.length > 0 && pw.value.length > 0;

	var parent = username.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = pw.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";

	if (username.value.length === 0)
	{
		var parent = username.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		username.setAttribute("placeholder", "Username: this field is mandatory");
	}

	if (pw.value.length === 0)
	{
		var parent = pw.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		pw.setAttribute("placeholder", "Password: this field is mandatory");
	}

	return test;
}

form.onsubmit = validateForm;
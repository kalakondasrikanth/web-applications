var form  = document.getElementById("regform");
var ebox = document.getElementById("emailbox");
ebox.oninput = function(){
	var emailText = ebox.value;
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var result = re.test(String(emailText).toLowerCase());

	var parent = ebox.parentNode;
	if(!result){
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		ebox.setAttribute("placeholder", "Please, insert a valid email address");
	}
	else{
		ebox.setAttribute("placeholder", "ebox");
		parent.style.borderRadius = "0rem";
		parent.style.backgroundColor = "transparent";
	}
}

var username = document.getElementById("usernamebox");
username.oninput = function(){
	username.setAttribute("placeholder", "Username");
	var parent = username.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
}

var name = document.getElementById("namebox");
document.getElementById("namebox").oninput = function(){
	document.getElementById("namebox").setAttribute("placeholder", "Name");
	var parent = document.getElementById("namebox").parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
}

var surname = document.getElementById("surnamebox");
surname.oninput = function(){
	surname.setAttribute("placeholder", "Surname");
	var parent = surname.parentNode;
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

var pw1 = document.getElementById("pw1box");
pw1.oninput = function(){
	pw1.setAttribute("placeholder", "Confirm Password");
	var parent = pw1.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
}


function validateForm()
{
	var emailText = ebox.value;
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var result = re.test(String(emailText).toLowerCase());

	if (!result){
		var parent = ebox.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		ebox.setAttribute("placeholder", "Please, insert a valid email address");
		return false;
	}

	var name = document.getElementById("namebox");
	var test = username.value.length > 0 && name.value.length > 0 && surname.value.length > 0;
	test = test && pw.value === pw1.value && pw.value.length != 0;

	var parent = ebox.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = username.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = name.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = surname.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = pw.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";
	var parent = pw1.parentNode;
	parent.style.borderRadius = "0rem";
	parent.style.backgroundColor = "transparent";

	if (username.value.length === 0)
	{
		var parent = username.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		username.setAttribute("placeholder", "Username: this field is mandatory");
	}

	if (name.value.length === 0)
	{
		var parent = name.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		name.setAttribute("placeholder", "Name: this field is mandatory");
	}

	if (surname.value.length === 0)
	{
		var parent = surname.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		surname.setAttribute("placeholder", "Surname: this field is mandatory");
	}

	if (pw.value != pw1.value)
	{
		var parent = pw.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		var parent = pw1.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		pw.setAttribute("placeholder", "The two typed passwords must match");
		pw1.setAttribute("placeholder", "The two typed passwords must match");
	}

	if (pw.value.length === 0){
		var parent = pw.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		pw.setAttribute("placeholder", "Password field cannot be empty");
	}

	if (pw1.value.length === 0){
		var parent = pw1.parentNode;
		parent.style.borderRadius = "1rem";
		parent.style.backgroundColor = "rgba(255,0,0,0.5)";
		pw1.setAttribute("placeholder", "Password field cannot be empty");
	}

	return test;
}

form.onsubmit = validateForm;

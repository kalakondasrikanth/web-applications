var parameter = location.search;
var temp = parameter.split("=");
var id = unescape(temp[1]);
document.getElementById("qid").value = id;
document.getElementById("qid").readOnly=true;


var answer = document.getElementById("answerbtn");
answer.addEventListener("click", function()
{
    var box = document.getElementById("box")
    if (box.style.display === "none")
    {
        box.style.display = "block";
    }
    else
    {
        box.style.display = "none";
    }
});
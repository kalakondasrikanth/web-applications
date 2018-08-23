$( document ).ready(function() 
{
	$('.section').each(function() 
	{
		// Save the two div references in a var so they can be called later within the event handler
		var translationDiv = $(this).children('.answer');
		var originalDiv = $(this).children('.title'); // Remove if you do not want to hide original text upon toggling

		translationDiv.hide(); // Sets initial translation to hide. You can alternatively do this via css such that all .english { display: none; }.

		$(this).click(function(e) 
		{
			translationDiv.toggle();
			originalDiv.toggle(); // Remove if you do not want to hide original text upon toggling
		});
	});
});
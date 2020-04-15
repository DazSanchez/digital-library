/**
 * 
 */

$(document).ready(() => {
	const $form = $("#documentForm");
	
	$form.submit(() => {
		$form.addClass("was-validated")
	})
});


function creer_question_insert_choix() {
	var nb_input =  $("input").length;
	var num_choix = nb_input - 12;
	return '<p>Choix ' + num_choix + '</p>' +
	   	   '<input type="text" name="text_choix_' + num_choix + '">';
}

function milieu_creer_scenario_insert_question(num_groupe, idScenario) {
	var num_question = $("#groupe" + num_groupe + " .question").length + 1;
	return '' +
'<script>' +
   '$(document).ready(function(){' +
		'$("#btn_delete_question_' + num_question + '_groupe_' + num_groupe + '").click(function(){' +
			'$.post("Controller", {action: "delete"}, function(data, status){' +
				'$("#question_' + num_question + '_groupe_' + num_groupe + '").hide();' +
			'});' +
		'});' +
	'});' +
'</script>' +
'<div class="row question" id="question_' + num_question + '_groupe_' + num_groupe + '">' +
	'<h5>Question ' + num_question + '</h5>' +
	'<form action="Controller" method="post" class="center">' +
		'<input type="hidden" name="idScenario" value=' + idScenario + '>' +
		'<input type="hidden" name="source" value="milieu_creer_scenario">' +
		'<input type="hidden" name="destination" value="creer_question">' +
		'<input class="btn btn-success" type="submit" value="Modifier">' +
	'</form>' +
	'<button id="btn_delete_question_' + num_question + '_groupe_' + num_groupe + '" type="button" class="btn btn-warning">Supprimer</button>' +
'</div>';
}

function milieu_creer_scenario_insert_groupe(idScenario) {
	var num_groupe = $(".groupe").length + 1;
	return '' +
'<script>' +
    '$(document).ready(function(){' +
		'$("#btn_delete_groupe_' + num_groupe + '").click(function(){' +
			'$.post("Controller", {action: "delete"}, function(data, status){' +
				'$("#groupe' + num_groupe + '").hide();' +
			'});' +
		'});' +
	'}); ' +
'</script>' +
'<div id="groupe' + num_groupe + '" class="groupe">' +
	'<div class="row">' +
		'<h4>Groupe ' + num_groupe + '</h4>' +
		'<form action="Controller" method="post" class="center">' +
			'<input type="hidden" name="idScenario" value=' + idScenario + '>' +
			'<input type="hidden" name="source" value="milieu_creer_scenario">' +
			'<input type="hidden" name="destination" value="creer_checkpoint">' +
			'<input class="btn btn-success" type="submit" value="Modifier">' +
		'</form>' +
		'<button id="btn_delete_groupe_' + num_groupe + '" type="button" class="btn btn-warning">Supprimer</button>' +
	'</div>' +
'<script>' +
    '$(document).ready(function(){' +
		'$("#btn_ajouter_question_dans_groupe_' + num_groupe + '").click(function(){' +
			'$.post("Controller", {action: "ajouter"}, function(data, status){' +
				'$("#pos_prochaine_question_dans_groupe_' + num_groupe + '").before(milieu_creer_scenario_insert_question(' + num_groupe + ',' + idScenario +  '));' +
			'});' +
		'});' +
	'}); ' +
'</script>' +
'<div id="pos_prochaine_question_dans_groupe_' + num_groupe + '"></div>' +
'<button id="btn_ajouter_question_dans_groupe_' + num_groupe + '" type="button" class="btn btn-success">Ajouter une question !</button>' +
'</div>'; 
}












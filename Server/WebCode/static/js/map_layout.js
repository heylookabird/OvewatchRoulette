function layout_map(map_list){

	table = document.getElementById('maps');
	var columnsPerRow = 3;
	var mapJson = JSON.parse(map_list);

	var tr = document.createElement('TR');
	var colCount = 0;


	for(var i=4;i<mapJson.length;i++){
		var map = mapJson[i];


		var td = document.createElement('TD');
		td.id = map.name;
		td.onclick = function(){ map_click(this.id); }
		colCount++;
		var map_image = document.createElement("IMG")
		map_image.src = '../static/images/' + map.name + '.jpg';
		map_image.className = "map_image";
		map_image.width = 300;
		map_image.height = 169;


        td.appendChild(map_image);
        tr.appendChild(td);

		if(colCount == columnsPerRow){
	        table.appendChild(tr);
	        tr = document.createElement('TR');
	        colCount = 0;
		}
    }

};

function map_click(td_id){

	window.location.href = '/map/' + td_id; //relative to domain

};
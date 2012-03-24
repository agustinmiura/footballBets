/*  
 Copyright (C) 2010 Miura Agustín
                    Rozanect Jose  
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
function doAjax(formID, url, destinationElementID) {        	
	var queryString = $("#" + formID).formSerialize();
    $.ajax({
        url: url,
        type: 'POST',
        data:queryString,
        error: function(responseHTML) {
        	var jSon = responseHTML.split(";");                	
            document.getElementById(destinationElementID).innerHTML = jSon[0];
            var originalColor = $('#'+destinationElementID).css("background-color");
            $('#'+destinationElementID).css("background-color", "#FF87A1").animate({opacity:1},13000,
                    function(){
                    	$('#'+destinationElementID).css("background-color", originalColor);
                        document.getElementById(destinationElementID).innerHTML = jSon[1];
            });
        },
        success: function(responseHTML) {
        	var jSon = responseHTML.split(";");                	
            document.getElementById(destinationElementID).innerHTML = jSon[1];
            var tempColor;                    
            if(jSon[0]==0){
            	tempColor = "#CFFAD3";
            }else{
            	tempColor = "#FF87A1";
            }

            var originalColor = $('#hiddenColor').css("background-color");
            $('#'+destinationElementID).css("background-color", tempColor).animate({opacity:1},4000,
                    function(){
                    	$('#'+destinationElementID).css("background-color", originalColor);
                        document.getElementById(destinationElementID).innerHTML = jSon[2];
            });                    
        }
    })        
}

function bringHTML(url, destinationElementID, collapseID){  
	$.ajax({
        url: url,
        type: 'POST',
        error: function(responseHTML) {        			
			document.getElementById(collapseID).innerHTML = "Collapse";
        	$('#'+collapseID).fadeIn('fast');
            document.getElementById(destinationElementID).innerHTML = responseHTML;                    
        },
        success: function(responseHTML) {
        	document.getElementById(collapseID).innerHTML = "Collapse";
            $('#'+collapseID).fadeIn('fast');
            document.getElementById(destinationElementID).innerHTML = responseHTML;                	
        }
    }) 	
}

function collapse(destinationElementID,callerID){
	document.getElementById(destinationElementID).innerHTML = '';        	
	$('#collapseTop'+callerID).fadeOut('fast');
}

var monitorConnection = setInterval('testConnection()', 5000);

function testConnection(){                            
	$.ajax({
	    url: '/',
	    type: 'GET',
	    error: function(responseHTML) {},
	    success: function(responseHTML) {                    
	    	if(responseHTML==""){
	        	document.getElementById('test').innerHTML = 'We have currently lost connection!';
	        	$('#test').fadeIn('slow');
	    	}else{                    		
	    		$('#test').fadeOut('slow');
	    		document.getElementById('test').innerHTML = '';                   		
	    	}                	
	    }
	});
}
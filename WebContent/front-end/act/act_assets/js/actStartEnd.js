	$(document).ready(function(){
	
	//scroll
		$("a.toscroll").on('click',function(e) {
				var url = e.target.href;
				var hash = url.substring(url.indexOf("#")+1);
				$('html, body').animate({
					scrollTop: $('#'+hash).offset().top
				}, 500);
				return false;
			});
//step1>2
		$("#next1").click(function(){
				$("#step2").css("visibility","visible");
				 $("#geocomplete").geocomplete({
				  map: ".map_canvas",
				  details: "form",
				  types: ["geocode", "establishment"],
				});

				$("#find").click(function(){
				  $("#geocomplete").trigger("geocode");
				  $("#next2").show();
				});
			});
	
//step2>3
	
		$("#next2").click(function(){
			$("#step3").css("visibility","visible");
			$("#actLocName").val($("#prename").val());
			$("#actLong").val($("#prelong").val());
			$("#actLat").val($("#prelat").val());
			$("#actAdr").val($("#preadr").val());
			$("#actLocID").val($("#prepost").val());
		});
		

//step3>4
		$("#next3").click(function(){
			$("#step4").css("visibility","visible");
		});

//step4===
	$("#repTime").change(function(){
		$("#papprep").css("visibility","visible");
		
	});
	
//step4>5
		$("#next4").click(function(){
			$("#step5").css("visibility","visible");
			$(".image-editor").cropit();
			$("#imguploader").on('change', function() {
				$("#imgprev").show();
		 	});
		});
	
	
//step5==

			$("input:radio[name=actPriS]").change(function(){
			if(this.value!='LV'){
				$("#priLVshow").hide();
			};
			
			if (this.value == 'LV') {
				$("#priLVshow").show();
				$("#actPriID").val('LV');
				var LVLV=$("#priLVsel").val();
				$("#actPriID").val(LVLV);
			}else if (this.value == 'PUBLIC') {
//				alert(this.value);
				$("#actPriID").val(1);
			}else if (this.value == 'CLUB') {
//				alert(this.value);
				$("#actPriID").val(2);
			}else if (this.value == 'CLUBS') {
//				alert(this.value);
				$("#actPriID").val(3)
			}else if (this.value == 'INV') {
//				alert(this.value);
				$("#actPriID").val(4);
			}else{
				alert("WAT");
			}
		});
			$("#priLVsel").change(function(){
				var LVLV=$("#priLVsel").val();
				$("#actPriID").val(LVLV);
				$("#LVLVLV").prop("checked", true);
			});
	
	        $("#testimg").click(function() {
          // Move cropped image data to hidden input
          var imageData = $('.image-editor').cropit('export');
          $('.hidden-image-data').val(imageData);
			
          // Print HTTP request params
          var formValue = $("#hns").serialize();
          $('#result-data').text(formValue);
          // Prevent the form from actually submitting
          return false;
			
			
			
			
        });
	//step5>6
	
		$("#next5").click(function(){
			$("#step6").css("visibility","visible");
		});
	
	//finalcheck
	        $("#ckfinal").click(function(){
			if($("#ckfinal").prop("checked")){
				$("#goSubmit").fadeIn();
		}});
		
		
		});



    
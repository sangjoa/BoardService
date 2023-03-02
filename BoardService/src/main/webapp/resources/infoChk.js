	var req;
	var data;
	
	function sendId() {
		var id = document.getElementById('id').value;
		req = new XMLHttpRequest();
		req.onreadystatechange = function(){			
			if (req.readyState == 4 && req.status == 200){
				var printId = "";
				printId = document.getElementById("idChk")
				
				if(req.responseText =="이미 존재하는 아이디 입니다"){
					document.getElementById("idChk").style.color = 'red'
					printId.innerHTML = req.responseText				
				}
				else if(req.responseText =="사용 가능한 아이디 입니다"){
					document.getElementById("idChk").style.color = 'green'
					printId.innerHTML = req.responseText				
				}
				else{
					document.getElementById("idChk").style.color = 'red'
					printId.innerHTML = req.responseText	
				}
			}
		}
		req.open('post','/ajax/idChk')
		req.send(id);
	}
	
	function sendName(){
		var uName = document.getElementById('userName').value;
		req = new XMLHttpRequest();
		req.onreadystatechange = function(){
			var printName = "";
			if (req.readyState == 4 && req.status == 200){
				printName = document.getElementById("nameChk")
				if(req.responseText =="이름을 올바르게 입력해주세요"){
					document.getElementById("nameChk").style.color = 'red'
					printName.innerHTML = req.responseText					
				}
				else
					printName.innerHTML = req.responseText
			}			
		}
		req.open('post','/ajax/nameChk')
		req.send(uName);	
	}
	
	function sendNick(){
		var nName = document.getElementById('nickName').value;
		req = new XMLHttpRequest();
		req.onreadystatechange = function(){
			var printNick = "";
			if(req.readyState == 4 && req.status == 200){
				printNick = document.getElementById('nickChk')
				if(req.responseText =="이미 등록된 닉네임 입니다"){
					document.getElementById("nickChk").style.color = 'red'
					printNick.innerHTML = req.responseText
				}
				else if(req.responseText == "사용 가능한 닉네임 입니다"){
					document.getElementById("nickChk").style.color = 'green'
					printNick.innerHTML = req.responseText			
				}
				else{
					document.getElementById("nickChk").style.color = 'red'
					printNick.innerHTML = req.responseText			
				}
			}
		}
			req.open('post','/ajax/nickChk')
			req.send(nName);
	}
	
	
	function emailAuth(){
		var email = document.getElementById('email').value;
		req = new XMLHttpRequest();
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200){
				data = req.responseText
			}
		}
		req.open('post','/ajax/emailChk')
		req.send(email);
	}
	
	function emailChk(){
		var num = document.getElementById('authNumber').value;
		
		var printSuccess = document.getElementById('auth-success')
		if(num == data){
			printSuccess.innerHTML = "<span style='color : green'>인증이 완료되었습니다 </span>";
			return true;
		}
		else{
			printSuccess.innerHTML = "<span style='color : red'>다시 입력해주세요 </span>";
			return false;			
		}
	}
	
	function pwCheck(){
		pw = document.getElementById('pw');
		confirm = document.getElementById('confirm');
		if(pw.value == confirm.value){
			document.getElementById('label').innerHTML = "<span style='color : green'>비밀번호가 일치합니다</span>";
		}
		else {
			document.getElementById('label').innerHTML = "<span style='color : red'>비밀번호를 확인해주세요 </span>";
			pw.value="";
			confirm.value="";
			pw.focus();
		}
	}
	

	function loginCheck(){
		id = document.getElementById('id').value;
		pw = document.getElementById('pw').value;
		
		if(id == ""){
			alert('아이디는 필수 항목입니다.');
		}else if(pw == ""){
			alert('비밀번호는 필수 항목입니다.');
		}else{
			document.getElementById('f').method = 'post';
			document.getElementById('f').submit();
		}
	}
	
	function allCheck(){
		
		mailchk = document.getElementById('authNumber').value;
		id = document.getElementById('id').value;
		pw = document.getElementById('pw').value;
		confirm = document.getElementById('confirm').value;
		name = document.getElementById('userName').value;
		
		if(id == ""){
			alert('아이디는 필수 항목입니다.');
		}else if(pw == ""){
			alert('비밀번호는 필수 항목입니다.');
		}else if(confirm == ""){
			alert('비밀번호 확인은 필수 항목입니다.');
		}else if(name == ""){
			alert('이름은 필수 항목입니다.');
		}else if(mailchk != data){
			alert('이메일 인증을 확인해주세요.')
		}else{
			document.getElementById('f').submit()
		}
	}
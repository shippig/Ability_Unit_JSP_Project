function loginConfirm()
{
	if(document.frm.id.value.length == 0)
	{
		alert("아이디가 비어있습니다.");
		document.frm.id.focus();
		return false;
	}
	
	if(document.frm.pwd.value.length == 0)
	{
		alert("비밀번호가 비어있습니다.");
		document.frm.pwd.focus();
		return false;
	}
	
	return true;
}


function registerConfirm()
{
	/*
	    1. 각 태그는 비어있으면 안된다.
		2. 비밀번호와 비밀번호 확인란이 같아야 한다.
		3. 아이디는 10글자 이하로 글자 제한
		4. 비밀번호 16글자 이하로 제한
		5. 비밀번호는 특수문자 1개이상을 포함해야 함
		6. 비밀번호는 대문자 1개이상을 포함해야 함
	 */
}
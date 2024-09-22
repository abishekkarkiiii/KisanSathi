function CreateAccount(){
    let obj={
        username:document.getElementById('username').value,
        password:document.getElementById('password').value,
        phoneNumber:$('#phonenumber').val(),
        name:$('#name').val()

    }
    console.log(obj)
    fetch('/CreateAccount',{
        method:'post',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(obj)
    }
       
) 
      document.getElementById('username').innerText=""
      document.getElementById('password').innerText=""
}
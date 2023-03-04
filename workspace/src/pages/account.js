import { useRef } from "react";
import { useRouter } from 'next/navigation';
import Image from 'next/image'

export default function Account (){

    const router = useRouter();
    const emailRef = useRef('');
    const passwordRef = useRef('');
    const storeRef = useRef(false);

    const logIn = () => {
        const User = {
            email: emailRef.current.value,
            password: passwordRef.current.value
        }
        fetch('http://localhost:8080/user/login',
            {
                method: 'POST',
                body: JSON.stringify(User),
                headers:{
                    'Content-Type': 'application/json',
                }
            }).then(res => res.json())
            .then(res => {
                console.log('login response token: ')
                console.log(res)})
            .then(res => {
                localStorage.setItem('jwt', res)
                localStorage.setItem('user', User)
            })
            .then(console.log(localStorage.getItem('jwt')))
               .finally(router.push('/'))
        
    };

    const register = () => {
        let role = 'USER'
        if (storeRef === 'true'){
            role = 'STORE_ADMIN'
        }
        const User = {
            email: emailRef.current.value,
            password: passwordRef.current.value,
            role: role
        }
        fetch('http://localhost:8080/user/register',
        {
            method: 'POST',
            body: JSON.stringify(User),
            headers:{
                'Content-Type': 'application/json',
            }
        }).then(res => res.json())
        .then(res => {
            console.log('registration response token: ')
            console.log(res)})
        .then(res => {
            localStorage.setItem('jwt', res)
            localStorage.setItem('user', User)
        })
  //      .finally(router.push('/'))
    };

        return (
        <div className="relative">
            <Image alt="image" 
          src="https://pngimg.com/uploads/amazon/amazon_PNG1.png"
          width={150}
          height={40}
          className='cursor-pointer relative m-5 self-center'
          />
            <div className="space-y-2 mt-5 items-center m-5 border border-2 rounded-md w-1/3 self-center">
            <form className="signup-container" >
                <input type ="text" placeholder="Email" ref={emailRef} className="signup-input m-5 border border-2 p-1 rounded-md" ></input>
                <br/>
                <input type ="password" placeholder="Password" ref={passwordRef} className="signup-input m-5 border border-2 p-1 rounded-md"></input>
                <br/>
                <span className="m-5">Create Store<input type="checkbox" id="role-checkbox" className="m-5 m-l-5" label="Store"/></span>
                <br/>
                <span>
                    <input type="button" value="Register" className="signup-button mt-auto button m-5" onClick={register}></input>
                    <input type="button" value="Log in" className="login-button mt-auto button m-5" onClick={logIn}></input>
                </span>
            </form>
            </div>
        </div>
        )
}

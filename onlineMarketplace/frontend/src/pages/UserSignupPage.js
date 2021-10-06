import React,{useState} from "react";
import Input from "../components/Input";
import { useTranslation } from 'react-i18next';
import ButtonWithProgress from "../components/ButtonWithProgress";
import {useApiProgress} from "../shared/ApiProgress";
import {useDispatch} from 'react-redux';
import {signUpHandler} from '../redux/authActions';
import { toast } from "react-toastify";

const UserSignupPage = () => {
    const [form ,setForm] = useState({
        username: null,
        fullName:null,
        email:null,
        password:null,
        passwordRepeat: null
    })

    const dispatch=useDispatch();
   
    const [errors, setErrors] = useState({});

    const onChange = event => {
        const {name,value}=event.target;

        setErrors((previousErrors)=>({...previousErrors,[name]:undefined}));
        setForm((previousForm)=>{return{...previousForm,[name]:value}})
    }

    const onClickSignUp = async event =>{
        event.preventDefault();

        const {username, fullName, password, email}=form;

        const body= {
            username,
            fullName,
            email,
            password,
        }

        try{
            await dispatch(signUpHandler(body));
            setForm({
                username:'',
                fullName:'',
                email:'',
                password:'',
                passwordRepeat:''
            });
            toast(t('User added'));

        }catch(error){
            if(error.response.data.validationErrors){
                setErrors(error.response.data.validationErrors);
                
            }

        }
    };
    
        const{username:usernameError,fullName:fullNameError,password:passwordError, email:emailError} = errors;
        const  pendingApiCallSignup  = useApiProgress('post','/api/1.0/users');
        const  pendingApiCallLogin  = useApiProgress('post','/api/1.0/auth');
        const pendingApiCall= pendingApiCallSignup || pendingApiCallLogin;
        const {t}=useTranslation();

        let passwordRepeatError;
        if(form.password!==form.passwordRepeat){
            passwordRepeatError=t('Password mismatch');
        }

        return(
            <div className="container">
                <form>
                    <h1 className="text-center">{t('Add User')}</h1>

                    <Input name="username" value={form.username} label={t("Username")+":"} error={usernameError} onChange={onChange}/>

                    <Input name="fullName" value={form.fullName} label={t("Name")+":"} error={fullNameError} onChange={onChange} />

                    <Input name="email" value={form.email} label={t("E-mail")+":"} error={emailError} onChange={onChange} />

                    <Input name="password" value={form.password} label={t("Password")+":"} error={passwordError} onChange={onChange} type="password"/>

                    <Input name="passwordRepeat" value={form.passwordRepeat} label={t("Password Repeat")+":"} error={passwordRepeatError} onChange={onChange} type="password"/>



                    {/*ButtonSignUp*/}
                    <div className= "text-center">

                        <ButtonWithProgress
                            onClick={onClickSignUp}
                            disabled={pendingApiCall || passwordRepeatError!==undefined}
                            pendingApiCall={pendingApiCall}
                            text={t('Save User')}
                        >

                        </ButtonWithProgress>
                    </div>
                </form>
            </div>
        );
    
}


export default  UserSignupPage;
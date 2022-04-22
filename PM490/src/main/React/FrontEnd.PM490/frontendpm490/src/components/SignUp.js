//Authors Phlipose and Angie
import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import withContext from "../withContext";
import axios from 'axios'

const initState = {
    username: "",
    password: "",
    fullname: "",
    phone: "",
    email: "",
    role: "",
    address:""
};

class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
        this.routerRef = React.createRef();
    }

    handleChange = e => this.setState({[e.target.name]: e.target.value, error: ""});

    save =  (e) => {
        e.preventDefault();
        const {username, password, fullname, phone, email, role, address} = this.state;
        console.log("#####"+this.state);
        if (!username || !password || !fullname || !phone || !email || !role) {
            this.setState({ flash: { status: 'is-danger', msg: 'Fill all fields!' }});
        }
        const res = axios.post(
            'http://localhost:8080/api/auth/signup',
            {username, password, email, role, address, fullname},
        ).catch((res) => {
            return {status: res.status, message: res.message}
        })
        console.log(res);
        if (res.status === 200) {
            this.setState(initState);
            window.alert("User registered successfully");
            window.location.replace("/login");
        } else {
            this.setState({ flash: { status: 'is-danger', msg: 'user already registered' }})
        }
    };


    render() {
        const {username, password, fullname, phone, email, role, address} = this.state;

        return  (
            <>
                <div className="hero is-primary ">
                    <div className="hero-body container">
                        <h4 className="title">Sign Up</h4>
                    </div>
                </div>
                <br/>
                <br/>
                <form onSubmit={this.save}>
                    <div className="columns is-mobile is-centered">
                        <div className="column is-one-third">
                            <div className="field">
                                <label className="label">Username: </label>
                                <input
                                    className="input"
                                    type="username"
                                    name="username"
                                    value={username}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Password: </label>
                                <input
                                    className="input"
                                    type="password"
                                    name="password"
                                    value={password}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Full Name: </label>
                                <input
                                    className="input"
                                    type="fullname"
                                    name="fullname"
                                    value={fullname}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Phone: </label>
                                <input
                                    className="input"
                                    type="number"
                                    name="phone"
                                    value={phone}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Email: </label>
                                <input
                                    className="input"
                                    type="email"
                                    name="email"
                                    value={email}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Address: </label>
                                <input
                                    className="input"
                                    type="address"
                                    name="address"
                                    value={address}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">Role: </label>
                                <select name="role"
                                        className="input"
                                        onChange={this.handleChange}
                                        value={role}>
                                    <option value="CUSTOMER">Customer</option>
                                    <option value="VENDOR">Vendor</option>
                                    <option value="CLIENT">Client</option>
                                </select>
                            </div>

                            {this.state.flash && (
                                <div className={`notification ${this.state.flash.status}`}>
                                    {this.state.flash.msg}
                                </div>
                            )}
                            <div className="field is-clearfix">
                                <button
                                    className="button is-primary is-outlined is-pulled-right"
                                    type="submit"
                                    onClick={this.save}
                                >
                                    Submit
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </>

        );
    }
}

export default withContext(SignUp);

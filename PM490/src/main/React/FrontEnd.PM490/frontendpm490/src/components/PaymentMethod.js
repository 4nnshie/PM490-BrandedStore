import React, {Component} from "react";
import withContext from "../withContext";
import axios from "axios";

const initState = {
    idMethod: 0,
    type: "",
    fullname: "",
    number: "",
    expireDate: "",
    cvv: "",
    zipcode: "",
    listMethods: []
};

class PaymentMethod extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
    }

    componentDidMount() {
        const {user} = this.props.context;
        if (user) {
            const listMethods = axios.get('http://localhost:8080/api/paymentmethod/index',
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + user.token
                    }
                });
            console.log(listMethods);
            this.setState({listMethods: listMethods.data})
        }
    }

    handleChange = e => this.setState({[e.target.name]: e.target.value, error: ""});

    save = async (e) => {
        e.preventDefault();
        const {type, fullname, number, expireDate, cvv, zipcode} = this.state;

        if (type && fullname && number && expireDate && cvv && zipcode) {

            const {user} = this.props.context;
            const res = await axios.post(
                'http://localhost:8080/api/paymentmethod/create',
                {type, fullname, number, expireDate, cvv, zipcode},
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + user.token
                    }
                },
            )
            if (res.status === 200){
                this.setState({idMethod: res.data.id})
                await this.pay(this.idMethod);
            }
            this.props.context.createProduct(
                {
                    type,
                    fullname,
                    number,
                    expireDate,
                    cvv,
                    zipcode
                },
                () => this.setState(initState)
            );

            this.setState(
                {flash: {status: 'is-success', msg: 'Payment method added'}}
            );
        } else {
            this.setState(
                {flash: {status: 'is-danger', msg: 'Please enter the fields'}}
            );
        }

    };

    pay = async ( idMethod) => {
        console.log("&&&&&&&&&&"+idMethod)
    }



    render() {
        const {idMethod, type, fullname, number, expireDate, cvv, zipcode} = this.state;
       // let {listMethods} = this.listMethods;
        const methods = Object.keys(this.listMethods || {});
        console.log(this.state.listMethods)
        return methods.length ? (
            <>
                <form>
                    <div className="field">
                        <p className="select">
                            <label className="label">Select your Payment Method: </label>
                            <select name="idMethod"
                                    className="input"
                                    onChange={this.handleChange}
                                    value={idMethod}>

                                {this.listMethods.map(met => <option value={met.id}>{met.name+"-"+met.number} </option>)}
                            </select>
                        </p>
                    </div>
                    <button
                        className="button is-primary is-outlined is-pulled-right"
                        type="submit"
                        onClick={this.pay(idMethod)}
                    >
                        Submit
                    </button>
                </form>
            </>
            ) : (
            <>
                <div className="hero is-primary ">
                    <div className="hero-body container">
                        <h4 className="title">Payment Methods</h4>
                    </div>
                </div>
                <br/>
                <br/>
                <form onSubmit={this.save}>
                    <div className="columns is-mobile is-centered">
                        <div className="column is-one-third">
                            <div className="field">
                                <label className="label">Payment Type </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="type"
                                    value={type}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">FullName: </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="fullname"
                                    value={fullname}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">number: </label>
                                <input
                                    className="input"
                                    type="number"
                                    name="number"
                                    value={number}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">expireDate: </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="expireDate"
                                    value={expireDate}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">CVV: </label>
                                <input
                                    className="input"
                                    type="number"
                                    name="cvv"
                                    value={cvv}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">zipcode: </label>
                                <input
                                    className="input"
                                    type="number"
                                    name="zipcode"
                                    value={zipcode}
                                    onChange={this.handleChange}
                                    required
                                />
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
                                    Pay
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </>

        );
    }
}

export default withContext(PaymentMethod);


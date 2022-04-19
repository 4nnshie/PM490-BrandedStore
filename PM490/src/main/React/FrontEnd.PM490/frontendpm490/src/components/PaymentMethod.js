import React, {Component} from "react";
import withContext from  "../withContext";

const initState = {
    type: "",
    fullname: "",
    number: "",
    expireDate: "",
    cvv:"",
    zipcode:"",
};
class PaymentMethod extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
    }
    render() {
        const {type, fullname, number, expireDate, cvv, zipcode} = this.state;
        const {user} = this.props.context;

    return(
        <>
           <div className="hero is-primary ">
             <div className="hero-body container">
                <h4 className="title">Payment Methods</h4>
             </div>
           </div>
           <br/>
           <br />
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
                            />
                        </div>
                        <div className="field">
                            <label className="label">CVV: </label>
                            <input
                                className="input"
                                type="number"
                                name="cvc"
                                value={cvv}
                                onChange={this.handleChange}
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
export default withContext(PaymentMethod);


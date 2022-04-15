import React, { Component } from "react";
import withContext from "../withContext";
import { Redirect } from "react-router-dom";
import axios from 'axios';

const initState = {
    name: "",
    color: "",
    idVendor: "",
    status: "",
    quantity:"",
    idCategory:"",
    price:"",
};

class CreateProduct extends Component {
    constructor(props) {
        super(props);
        this.state = initState;
    }

    save = async (e) => {
        e.preventDefault();
        const { name, color, idVendor,status,quantity, idCategory, price } = this.state;
       // const token = Buffer.from(`${username}:${password}`, 'utf8').toString('base64')
       /* const url = 'https://...'
        const data = {
            ...
        }
        axios.post(url, data, {
            headers: {
                'Authorization': `Basic ${token}`
            },
        })
        */
        if (name && price) {
            const id = Math.random().toString(36).substring(2) + Date.now().toString(36);
            const { user } = this.props.context;
            await axios.post(
                'http://localhost:8080/api/product/saveproduct',
                { name, color, idVendor: user.id, status: "APPROVED",quantity, idCategory, price },
                {headers: {'Content-Type': 'application/json',
                        'Authorization': 'Bearer '+user.token}
            },

        )

            this.props.context.createProduct(
                {
                    name,
                    color,
                    idVendor,
                    status,
                    quantity,
                    idCategory,
                    price
                },
                () => this.setState(initState)
            );
            this.setState(
                { flash: { status: 'is-success', msg: 'Product created successfully' }}
            );

        } else {
            this.setState(
                { flash: { status: 'is-danger', msg: 'Please enter name and price' }}
            );
        }
    };

    handleChange = e => this.setState({ [e.target.name]: e.target.value, error: "" });

    render() {

        const { name, color,quantity, idCategory, price } = this.state;
        const { user } = this.props.context;
        console.log("###################"+user.token);
        console.log("----"+user.username);

        return !(user && user.role === "VENDOR" ) ? (
            <Redirect to="/" />
        ) : (
            <>
                <div className="hero is-primary ">
                    <div className="hero-body container">
                        <h4 className="title">Create Product</h4>
                    </div>
                </div>
                <br />
                <br />
                <form onSubmit={this.save}>
                    <div className="columns is-mobile is-centered">
                        <div className="column is-one-third">
                            <div className="field">
                                <label className="label">Product Name: </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="name"
                                    value={name}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">color: </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="color"
                                    value={color}
                                    onChange={this.handleChange}
                                    required
                                />
                            </div>
                            <div className="field">
                                <label className="label">quantity: </label>
                                <input
                                    className="input"
                                    type="number"
                                    name="quantity"
                                    value={quantity}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">category: </label>
                                <input
                                    className="input"
                                    type="text"
                                    name="idCategory"
                                    value={idCategory}
                                    onChange={this.handleChange}
                                />
                            </div>
                            <div className="field">
                                <label className="label">price: </label>
                                <textarea
                                    className="input"
                                    type="number"
                                    rows="2"
                                    style={{ resize: "none" }}
                                    name="price"
                                    value={price}
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

export default withContext(CreateProduct);
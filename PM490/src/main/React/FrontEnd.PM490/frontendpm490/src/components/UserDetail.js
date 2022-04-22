import React, {Component} from "react";
import withContext from "../withContext";
import axios from 'axios';
import { useParams } from 'react-router-dom'

const initState = {
    userlogged: null,
    userHandle: null,
    listUsers: [],
};

// const { id } = useParams();

class UserDetail extends Component {
    constructor(props) {
        super(props);
    }

    async componentDidMount() {
        const {user} = this.props.context;

        const { id } = useParams();

        const userDetail = await axios.get('http://localhost:8080/api/user/user/'+2,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + user.token
                }
            }
        );
        this.setState({
            userDetail: userDetail.data
        })
    }

    render() {
        {}
        return <h1>Hello, {this.props.name}</h1>;
    }
}

export default withContext(UserDetail);

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class Delete extends React.Component {
        constructor() {
            super();
            this.state = {
                id: '',
                firstName: '',
                lastName: '',
                email: '',
                dateOfBirth: '',
                data: []
            };
            this.onClick = this.onClick.bind(this);
            this.delete = this.delete.bind(this);
        }
        componentDidMount() {
            this.setState({
                id: this.props.user.id,
                firstName: this.props.user.firstName,
                lastName: this.props.user.lastName,
                email: this.props.user.email,
                dateOfBirth: this.props.user.dateOfBirth,
                data: this.props.user.data
            })
        }
        componentWillReceiveProps(nextProps) {
            this.setState({
                id: nextProps.user.id,
                firstName: nextProps.user.firstName,
                lastName: nextProps.user.lastName,
                email: nextProps.user.email,
                dateOfBirth: nextProps.user.dateOfBirth,
                data: nextProps.user.data
            })
        }


        onClick(e) {
            this.delete(this);
            this.updateAfterDelete(this);
        }
        delete(e) {
            axios.delete('http://localhost:8080/user', {
                    params: {
                        id: e.state.id
                    }
                })
                .then(function(response) {
                    this.setState({
                        data: response.data
                    });

                    axios.get('http://localhost:8080/user')
                        .then(function(response) {
                            e.setState({
                                data: response.data
                            });
                        });

                });
        }

        updateAfterDelete(e) {

            axios.get('http://localhost:8080/user')
                .then(function(response) {
                    e.setState({
                        data: response.data
                    });
                });
        }

    render(){
      return (
        <Button bsStyle="danger" bsSize="small" onClick={this.onClick}>
         <Link to={{pathname: '/'}} style={{ textDecoration: 'none' }}>
               <span className="glyphicon glyphicon-remove"></span>
         </Link>
        </Button>
        )
     }
}
export default Delete;

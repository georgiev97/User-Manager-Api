import React from 'react';
import {Button} from 'react-bootstrap';
import Modal from 'react-modal';
import axios from 'axios';
import {Link} from 'react-router-dom';
var querystring = require('querystring');

class Add extends React.Component {
    constructor() {
        super();
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            dateOfBirth: '',
            messageFromServer: '',
            modalIsOpen: false
        }

        this.onClick = this.onClick.bind(this);
        this.handleTextChange = this.handleTextChange.bind(this);
        this.insertNewUser = this.insertNewUser.bind(this);
        this.openModal = this.openModal.bind(this);
        this.closeModal = this.closeModal.bind(this);
    }
    openModal() {
        this.setState({
            modalIsOpen: true
        });
    }
    closeModal() {
        this.setState({
            modalIsOpen: false,
            firstName: '',
            lastName: '',
            email: '',
            dateOfBirth: '',
            messageFromServer: ''
        });
    }

    onClick(e) {
        this.insertNewUser(this);
    }
    insertNewUser(e) {
        var user = {
            firstName: e.state.firstName,
            lastName: e.state.lastName,
            email: e.state.email,
            dateOfBirth: e.state.dateOfBirth
        }
        axios.post('http://localhost:8080/user', user).then(function(response) {
            e.setState({
                messageFromServer: response.data
            });
        });
    }
    handleTextChange(e) {
        if (e.target.name == "firstName") {
            this.setState({
                firstName: e.target.value
            });
        }
        if (e.target.name == "lastName") {
            this.setState({
                lastName: e.target.value
            });
        }
        if (e.target.name == "email") {
            this.setState({
                email: e.target.value
            });
        }

        if (e.target.name == "dateOfBirth") {
            this.setState({
                dateOfBirth: e.target.value
            });
        }
    }

render() {
   if(this.state.messageFromServer == ''){
      return (
       <div>
        <Button bsStyle="success" bsSize="small" onClick={this.openModal}><span className="glyphicon glyphicon-plus"></span></Button>
           <Modal isOpen={this.state.modalIsOpen} onRequestClose={this.closeModal} contentLabel="Add User" className="Modal">
               <Link to={{pathname: '/' }} style={{ textDecoration: 'none' }}>
                    <Button bsStyle="danger" bsSize="mini" onClick={this.closeModal}><span className="closebtn glyphicon glyphicon-remove"></span></Button>
               </Link>
                     <br/>
               <fieldset>
                   <label for="firstName">First Name:</label>
                   <input type="text" id="firstName" name="firstName" value={this.state.firstName} onChange={this.handleTextChange}></input>
                   <label for="lastName">Last Name:</label>
                   <input type="text" id="lastName" name="lastName" value={this.state.lastName} onChange={this.handleTextChange}></input>
                   <label for="email"> Email:</label>
                   <input type="email" id="email" name="email" value={this.state.email} onChange={this.handleTextChange}></input>
                   <label for="dateOfBirth">Date Of Birth:</label>
                   <input type="date" id="dateOfBirth" name="dateOfBirth" value={this.state.dateOfBirth} onChange={this.handleTextChange}></input>
               </fieldset>
               <div className='button-center'>
                   <br/>
                   <Button bsStyle="success" bsSize="small" onClick={this.onClick}>Add New User</Button>
               </div>
           </Modal>
       </div>
      )
   }
   else{
    return (
     <div>
       <Button bsStyle="success" bsSize="small" onClick={this.openModal}><span className="glyphicon glyphicon-plus"></span></Button>
         <Modal isOpen={this.state.modalIsOpen} onAfterOpen={this.afterOpenModal} onRequestClose={this.closeModal} contentLabel="Add User" className="Modal">
             <div className='button-center'>
                     <h3>{this.state.messageFromServer}</h3>
                 <Link to={{pathname: '/'}} style={{ textDecoration: 'none' }}>
                    <Button bsStyle="success" bsSize="mini" onClick={this.closeModal}>Close the Dialog</Button>
                 </Link>
             </div>
         </Modal>
     </div>
     )
    }
   }
}
export default Add;

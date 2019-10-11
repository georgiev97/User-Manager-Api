import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import Add from './Add';
import Update from './Update';
import Delete from './Delete';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import SearchField from "react-search-field";
import { Tab, Tabs, Form, FormGroup } from 'react-bootstrap';
import styles from '../css/App.css';


    const options = [
          { label:'Select an option', value:''},
          { label: 'Id Asc', value: "ID/ASC" },
          { label: 'First Name Asc', value: "FIRSTNAME/ASC" },
          { label: 'Last Name Asc', value: "LASTNAME/ASC" },
          { label: 'Email Asc', value: "EMAIL/ASC" },
          { label: 'Date Of Birth Asc', value: "DATEOFBIRHT/ASC" },
          { label: 'Id Desc', value: "ID/DESC" },
          { label: 'First Name Desc', value: "FIRSTNAME/DESC" },
          { label: 'Last Name Desc', value: "LASTNAME/DESC" },
          { label: 'Email Desc', value: "EMAIL/DESC" },
          { label: 'Date Of Birth Desc', value: "DATEOFBIRHT/DESC" },
        ]
     const defaultOption=options[0]

export default class App extends React.Component {
    constructor() {
        super();
        this.state = {
            data: []
        };
        this.getData = this.getData.bind(this);
        this.findByEmail = this.findByEmail.bind(this);
        this.findById = this.findById.bind(this);
        this.search= this.search.bind(this);
        this.sortUser = this.sortUser.bind(this);
    }

    componentWillReceiveProps(nextProps) {
            this.getData();
      }

    componentDidMount() {
        this.getData();
    }

    getData() {
        let self = this;
        axios.get('http://localhost:8080/user')
            .then(function(response) {
                self.setState({
                    data: response.data
                });
            });
    }

    findByEmail(email) {
        if(email==""){
            this.getData();
        }else{
        let self = this;
        axios.get('http://localhost:8080/user/email/' + email)
            .then(function(response) {
                self.setState({
                    data: response.data
                });
            });
            }
    }

    findById(id) {
            if(id==''){
                this.getData();
            }else{
            let self = this;
            axios.get('http://localhost:8080/user/id/' + id)
                .then(function(response) {
                    self.setState({
                        data: [response.data]
                    });
                });
                }
        }

     search(param){
        if(param==''){
            this.getData();
        }else if(param.includes('@')){
            let self=this;
            axios.get('http://localhost:8080/user/email/' + param)
                .then(function(response){
                    self.setState({
                        data:[response.data]
                    });
                })
        }else{
           let self=this;
           axios.get('http://localhost:8080/user/id/' + param)
                .then(function(response){
                     self.setState({
                        data:[response.data]
                   });
              })
        }

     }

    sortUser(event){
    let self=this;
    var params=event.value.split('/');
    defaultOption=event.value;
    if(params[0]=='' || params[1]==''){
        this.getData();
    }
    axios.get('http://localhost:8080/user/sortUser/' + params[0]+'/'+params[1])
            .then(function(response) {
                self.setState({
                    data: response.data
                });
            });
    }

render() {
    return (
     <div>

        <table>
            <thead>
                <tr>
                    <th className='desc-col'><Add/></th>
                    <th className='desc-col'><SearchField placeholder="Search..." searchText="" onSearchClick={this.search} classNames="test-class"/></th>
                    <th className='desc-col'><div class="dropdown"> <Dropdown options={options} onChange={this.sortUser}  value={defaultOption} placeholder="Select an option" /></div></th>
                </tr>
            </thead>
        </table>
        <p></p>
         <table>
             <thead>
                 <tr>
                     <th className='button-col'>Id</th>
                     <th className='button-col'>First Name</th>
                     <th className='button-col'>Last Name</th>
                     <th className='button-col'>Email</th>
                     <th className='button-col'>Date Of Birth</th>
                     <th className='button-col'>Update</th>
                     <th className='button-col'>Delete</th>
                 </tr>
             </thead>
            <tbody>
                      {
                                    this.state.data.map((user) => {
                                                   return  <tr>
                                                   <td className='button-col'>{user.id}</td>
                                                   <td className='button-col'>{user.firstName}</td>
                                                   <td className='button-col'>{user.lastName}</td>
                                                   <td className='button-col'>{user.email}</td>
                                                   <td className='button-col'>{user.dateOfBirth}</td>
                                                   <td className='button-col'><Update user={user}/></td>
                                                   <td className='button-col'><Delete user={user}/></td>
                                                   </tr>
                                                 })
                                 }
                        </tbody>
         </table>
     </div>
    );
  }
}

import logo from './logo.svg';
import './App.css';
import React from 'react';
import Home from './Home';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import StudentList from './Student/StudentList';
import StudentEdit from './Student/StudentEdit';
import ProfessorList from './Professor/ProfessorList';
import ProfessorEdit from './Professor/ProfessorEdit';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path='/' element={<Home />}/>
        <Route path='/students' exact={true} element={<StudentList />}/>
        <Route path='/student/:id' element={<StudentEdit />}/> 
        <Route path='/professors' exact={true} element={<ProfessorList />}/>
        <Route path='/professor/:id' element={<ProfessorEdit />}/>
      </Routes>
    </Router>
  );
}

export default App;

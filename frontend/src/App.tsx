import React from 'react';
import MainHeader from './Components/MainHeader';
import { GlobalStyle } from './Styles/GlobalStyles';
import LoginForm from './Components/LoginForm';

function App() {
  return (
    <div className="App">
      <GlobalStyle />
      <MainHeader></MainHeader>
      <LoginForm/>
    </div>
  );
}

export default App;

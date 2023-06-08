import React from 'react';
import { GlobalStyle } from './Styles/GlobalStyles';

import Login from './Pages/Login';
import Home from './Pages/Home';
import Register from './Pages/Register';

function App() {
  return (
    <div className="App">
      <GlobalStyle />
      <Register/>
    </div>
  );
}

export default App;

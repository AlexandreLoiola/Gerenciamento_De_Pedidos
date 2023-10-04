import React from 'react';
import { GlobalStyle } from './Styles/GlobalStyles';

import {Outlet} from 'react-router-dom';

import Footer from './Components/Footer';

function App() {
  return (
    <div className="App">
      <GlobalStyle />
      <Outlet />
      <Footer/>
    </div>
  );
}

export default App;
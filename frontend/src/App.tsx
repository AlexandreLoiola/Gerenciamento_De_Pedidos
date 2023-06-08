import React from 'react';
import { GlobalStyle } from './Styles/GlobalStyles';

import {Outlet} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <GlobalStyle />
      <Outlet />
    </div>
  );
}

export default App;

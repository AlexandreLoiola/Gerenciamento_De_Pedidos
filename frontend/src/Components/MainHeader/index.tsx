import React from 'react';
import { HeaderContainer } from './styles';
import DividerLine from '../Divisor';

const MainHeader: React.FC = () => {
  return (
    <HeaderContainer>
      <h1>Sistema de Gerenciamento <br/> de Pedidos</h1>
      <DividerLine/>
    </HeaderContainer>
  );
}

export default MainHeader;
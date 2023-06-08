import React from 'react';
import { HeaderContainer } from './styles';
import DividerLine from '../Divisor';
import HeaderTitle from '../HeaderTitle';

interface Iprops {
  title: String;
}

const MainHeader: React.FC<Iprops> = (props) => {
  return (
    <HeaderContainer>
      <HeaderTitle title={props.title} />
      <DividerLine/>
    </HeaderContainer>
  );
}

export default MainHeader;
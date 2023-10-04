import React from 'react';
import { FooterContainer, FooterLink } from './styles';

const Footer: React.FC = () => {
    return (
      <FooterContainer>
            Desenvolvido por &nbsp;<FooterLink href="https://github.com/AlexandreLoiola" target="_blank" rel="noopener noreferrer">Alexandre Loiola</FooterLink>
      </FooterContainer>
    );
  };
  
  export default Footer;
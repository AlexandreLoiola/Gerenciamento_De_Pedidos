import React from "react";
import MainHeader from "../../Components/Header";

import BigButton from '../../Components/BigButton'

const Home: React.FC = () => {
    return (
        <>
            <MainHeader title={'Seja bem-vindo, Alexandre!'}/>
            <BigButton title={'VER PRODUTOS'} redirectTo={'/catalogo-de-produtos'}/>
            <BigButton title={'PEDIDO'} redirectTo={'/gerar-pedido'}/>
        </>
    )
}

export default Home;
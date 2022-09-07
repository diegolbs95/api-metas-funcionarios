import logo from '../../assets/img/logo.svg'

import './styles.css'

function App() {
  return(
    <header>
        <div className="mtfuncionario-logo-container">
            <img src={logo} alt="MetaFuncionario" />
            <h1>Vendas Funcionario</h1>
            <p>
             Desenvolvido por 
            <a href="https://github.com/diegolbs95"> @Diego Luis</a>
            </p>
        </div>
    </header>
  )
}

export default App
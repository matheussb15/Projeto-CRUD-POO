package univs.edu.funcionario;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import univs.edu.util.HibernateUtil;


public class FuncionarioDAO {
    private Session sessao;
    private Transaction transacao;
    
    public void salvar(Funcionario funcionario){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        if(funcionario.getIdFuncionario() == 0){
            sessao.save(funcionario);
        }else{ editar(funcionario);}
        transacao.commit();
        sessao.close();
    }
    
    public void excluir(Funcionario funcionario){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        sessao.delete(funcionario);
        transacao.commit();
        sessao.close();
    }
    
    public void editar(Funcionario funcionario){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        sessao.update(funcionario);
        transacao.commit();
        sessao.close();
    }
    
    public Funcionario pesquisar(int id){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Funcionario funcionario = (Funcionario) sessao.
                createCriteria(Funcionario.class)
                .add(Restrictions.eq("idFuncionario", id))
                .uniqueResult();
        sessao.close();
        return funcionario;
    }
      
    public Funcionario autenticarFuncionario(String login, String senha){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Funcionario funcionario = (Funcionario) sessao.
                createCriteria(Funcionario.class)
                .add(Restrictions.eq("login:", login))
                .add(Restrictions.eq("senha:", senha))
                .uniqueResult();
        sessao.close();
        
        return funcionario != null ?funcionario:null;
    }
    
    public List<Funcionario> listarFuncionario(){
        sessao = HibernateUtil.
                getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
       List<Funcionario> funcionarios = sessao.
                createCriteria(Funcionario.class).list();
        sessao.close();
        return funcionarios;
    }
}

package com.eda.eadaluno.interfaceActivity;

import com.eda.eadaluno.model.Questao;

public interface IQuestaoActivity {

    void createNewQuestao(String texto, String link_imagem, String pontuacao, String validacao);

    void updateQuestao(Questao questao);

    void onLongQuestaoSelected(Questao questao);

    void deleteQuestao(Questao questao);

}

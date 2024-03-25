#INCLUDE 'PROTHEUS.CH'
#INCLUDE "FWMVCDEF.CH"

user function MvcCApi()
    local oBrowse := FwLoadBrw("MvcCApi")

    oBrowse:Activate()
return

static function BrowseDef()
    local oBrowse  := FwmBrowse():New()

    oBrowse:SetAlias("ZI2")
    oBrowse:SetDescription("Tela de Mvc Teste")
    oBrowse:SetOnlyFields({"campos"})
return oBrowse

static function MenuDef()
    local aMenu :={}
    //1- pesquisar | 2 - visualizar| 3 - incluir | 4 - alterar| 5 - excluir| 6 - outras funções
    ADD OPTION aMenu    TITLE 'pesquisar'   ACTION 'VIEWDEF.MvcCApi'    OPERATION 1  ACCESS 0
    ADD OPTION aMenu    TITLE 'visualizar'  ACTION 'VIEWDEF.MvcCApi'    OPERATION 2  ACCESS 0
    ADD OPTION aMenu    TITLE 'incluir'     ACTION 'VIEWDEF.MvcCApi'    OPERATION 3  ACCESS 0
    ADD OPTION aMenu    TITLE 'alterar'     ACTION 'VIEWDEF.MvcCApi'    OPERATION 4  ACCESS 0
    ADD OPTION aMenu    TITLE 'excluir'     ACTION 'VIEWDEF.MvcCApi'    OPERATION 5  ACCESS 0
return aMenu


static function ModelDef()
    local oModel := NIL
    local oStruct := FwFormStruct(1,"tabela")
                                /*mlValid*/
    oModel:= MPFormModel():New('MvcCApiM',/*bPre*/,/*bPos*/,/*bCommit*/,/*bCancel*/)
    oModel:AddFields("FORM",,oStruct)

    oModel:SetPrimaryKey({"chaves primaria"})
    oModel:SetDescription("Modelo de dados Teste")
    oModel:GetModel("FORM"):SetDescription("Formulario de cadastro")

return oModel



static function ViewDef()
    local oView  := NIL
    local oModel := FwLoadModel("MvcCApi")
    local oStruct := FwFormStruct(2,"tabela")

    oView := FwFormView():New()
    oView:SetModel(oModel)
    oView:Addfield("VIEW",oStruct,"FORM")
    oView:CreateHorizontalBox("TELA", 100)

    oView:SetOWnerView("VIEW","TELA")

    oView:SetCloseOnOk({|| .T.})
    //oView:EnableTitleView("VIEW","View teste")

return oView

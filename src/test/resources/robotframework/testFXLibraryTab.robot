*** Settings ***
Library           TestFXLibrary
Suite Setup       Start Application     testapp.FxApplicationUnitTest
Suite Teardown    Close Application

*** Test Cases ***

Test Get Tab Pane Tabs
    @{default_tabs}=    Set Variable    Tab One    Tab Two    Tab Three     Disabled Tab
    @{tabs}=     Get Tab Pane Tabs     \#tabPane
    Should Be Equal     ${tabs}     ${default_tabs}

Test Get Selected Tab
    ${default_selection}=    Set Variable     Tab One
    ${selected_tab}=    Get Selected Tab   \#tabPane
    Should Be Equal      ${default_selection}     ${selected_tab}

Test Select Tab By Text
    ${tab_text}=     Set Variable     Tab Two
    Select Tab By Text      \#tabPane       ${tab_text}
    ${selected_tab}=    Get Selected Tab   \#tabPane
    Should Be Equal      ${selected_tab}     ${tab_text}

Test Tab Item Should Be Selected
    ${default_tab}=     Set Variable     Tab Two
    Tab Item Should Be Selected     \#tabPane     ${default_tab}
    ${selected_tab}=    Get Selected Tab   \#tabPane
    Should Be Equal      ${selected_tab}     ${default_tab}

Test Tab Item Should Not Be Selected
    ${not_selected_tab}=     Set Variable     Tab One
    Tab Item Should Not Be Selected     \#tabPane     ${not_selected_tab}
    ${selected_tab}=    Get Selected Tab   \#tabPane
    Should Not Be Equal      ${selected_tab}     ${not_selected_tab}

Test Tab Should Be Enabled
    ${enabled_tab_text}=     Set Variable    Tab One
    Tab Should Be Enabled    \#tabPane     ${enabled_tab_text}

Test Tab Should Be Disabled
    ${disabled_tab_text}=     Set Variable    Disabled Tab
    Tab Should Be Disabled    \#tabPane     ${disabled_tab_text}

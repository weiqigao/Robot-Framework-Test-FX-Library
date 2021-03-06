*** Settings ***
Library           TestFXLibrary
Suite Setup       Start Application     testapp.FxApplicationUnitTest
Suite Teardown    Close Application

*** Test Cases ***

Test Get Scroll Pane Max Horizontal Value
    ${default_hor_max}=     Set Variable    0.5
    ${max_hor_value}=   Get Scroll Pane Max Horizontal Value    \#scrollPane
    Should Be Equal     ${max_hor_value}      ${${default_hor_max}}

Test Get Scroll Pane Min Horizontal Value
    ${default_hor_min}=     Set Variable    0.1
    ${min_hor_value}=   Get Scroll Pane Min Horizontal Value    \#scrollPane
    Should Be Equal     ${min_hor_value}        ${${default_hor_min}}

Test Get Scroll Pane Max Vertical Value
    ${default_ver_max}=     Set Variable    0.4
    ${max_ver_value}=   Get Scroll Pane Max Vertical Value    \#scrollPane
    Should Be Equal     ${max_ver_value}      ${${default_ver_max}}

Test Get Scroll Pane Min Vertical Value
    ${default_ver_min}=     Set Variable    0.2
    ${min_ver_value}=   Get Scroll Pane Min Vertical Value    \#scrollPane
    Should Be Equal     ${min_ver_value}        ${${default_ver_min}}

Test Get Scroll Pane Horizontal Value
    ${default_hor_value}=     Set Variable    0.23
    ${hor_value}=   Get Scroll Pane Horizontal Value    \#scrollPane
    Should Be Equal     ${hor_value}        ${${default_hor_value}}

Test Get Scroll Pane Vertical Value
    ${default_ver_value}=     Set Variable    0.35
    ${ver_value}=   Get Scroll Pane Vertical Value    \#scrollPane
    Should Be Equal     ${ver_value}        ${${default_ver_value}}

Test Set Scroll Pane Horizontal Value
    ${new_value}=     Set Variable    0.25
    Set Scroll Pane Horizontal Value    \#scrollPane    ${new_value}
    ${hor_value}=   Get Scroll Pane Horizontal Value    \#scrollPane
    Should Be Equal     ${hor_value}      ${${new_value}}

Test Set Scroll Pane Vertical Value
    ${new_value}=     Set Variable    0.29
    Set Scroll Pane Vertical Value    \#scrollPane    ${new_value}
    ${ver_value}=   Get Scroll Pane Vertical Value    \#scrollPane
    Should Be Equal     ${ver_value}        ${${new_value}}

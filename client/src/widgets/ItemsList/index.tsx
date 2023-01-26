import React, {useEffect, useState} from 'react';

import { Delete, Edit } from '@mui/icons-material';
import {Box, Button, IconButton, InputLabel, Stack, TextareaAutosize} from '@mui/material';
import {RolesEnum, UserType} from "../../services/roles/role";
import {UserFields} from "../../components/UsersModal/UsersForm";
import './TextStyles.css'
import { getCurrentUser, getUserRole } from "../../auth";

type Props = {
  title: string;
  data?: Array<{ id: string; info: string }>;
  onDeleteItem?: (id: string) => void;
  onEditItem?: (id: string) => void;
  onItemClick?: (id: string) => void;
  onCreate?: () => void;
  date_of_extract?: string;
  multiData?: Array<{id: string;
                    timetable: Array<{dayOfWeek: string; timeBegin: string; timeEnd: string}>;
                    doctorInfo: string}>
  analysisData?: Array<{ id: string; analysis: string; analysisResult: string }>;
};
export const ItemsList: React.FC<Props> = ({ title, data, onDeleteItem, onEditItem, onItemClick, onCreate, date_of_extract, multiData , analysisData }) => {
    const [userRole, setUserRole] = useState();
    const [user, setUser] = useState<UserType>();

    useEffect(() => {
        setUserRole(getUserRole());
        setUser(getCurrentUser());

        console.log(user);
        console.log(userRole);
    }, []);

    if (title == "Расписание") {
        return (
            <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                     alignItems={'center'}>
                    <h2>{title}</h2>
                </Box>
                {!data?.length && <div>Пусто</div>}
                {data?.length &&
                    <Stack gap={2} width={'100%'}>
                        <Box className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                             sx={{backgroundColor: '#e6e6e6'}}>
                            <Stack
                                direction="column"
                                justifyContent="space-between"
                                alignItems="center"
                                spacing={1}
                                padding={'10px'}
                            >
                                {
                                    data?.map(({info, id}) => (
                                        <div style={{width: '100%'}}><b>{info.split(":")[0]}:</b> {info.split(" ")[1]} </div>
                                    ))
                                }
                            </Stack>
                        </Box>
                    </Stack>
                }
            </Box>
        )
    } else if (title == "Расписание врачей") {
        return (
            <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                     alignItems={'center'}>
                    <h2>{title}</h2>
                    <Button
                        size={'small'}
                        style={{height: '40px', marginLeft: '15px'}}
                        variant="contained"
                        color="secondary"
                        disabled={!onCreate}
                        onClick={onCreate}
                    >
                        Добавить расписание
                    </Button>
                </Box>
                {!multiData?.length && <div>Пусто</div>}
                <Stack gap={2} width={'100%'}>
                    {multiData?.map(({id, timetable, doctorInfo}) => (
                        <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                             sx={{backgroundColor: '#e6e6e6'}}>
                            <Stack
                                direction="column"
                                justifyContent="space-between"
                                alignItems="center"
                                spacing={1}
                                padding={'10px'}
                            >
                                <div style={{width: '100%'}}>
                                    <b>Врач:</b> {doctorInfo}
                                </div>
                                {timetable && timetable.map(position => (
                                    <div key={position.dayOfWeek} style={{width: '100%'}}>
                                        <b>{position.dayOfWeek}: </b>
                                        {position.timeBegin}-{position.timeEnd}
                                    </div>
                                ))}

                                <Stack justifyContent="center" direction="row" gap="16px" className="edit-buttons">
                                    <IconButton
                                        onClick={(e: React.MouseEvent) => {
                                            e.stopPropagation();
                                            if (onEditItem) onEditItem(id);
                                        }}
                                    >
                                        <Edit/>
                                    </IconButton>

                                    <IconButton
                                        onClick={(e: React.MouseEvent) => {
                                            e.stopPropagation();
                                            if (onDeleteItem) onDeleteItem(id);
                                        }}
                                    >
                                        <Delete/>
                                    </IconButton>
                                </Stack>
                            </Stack>
                        </Box>
                    ))}
                </Stack>
            </Box>
        )
    } else if (title == "Приёмная") {
        if (userRole == RolesEnum.DOCTOR)
            return (
                <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                    <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                         alignItems={'center'}>
                        <h2>{title}</h2>
                    </Box>
                    {!data?.length && <div>Пусто</div>}
                    <Stack gap={2} width={'100%'}>
                        {data?.map(({info, id}) => (
                            <>
                                {info.split(" ")[0] == user?.idDoctor &&
                                    <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                         sx={{backgroundColor: '#e6e6e6'}}>
                                        <Stack
                                            direction="column"
                                            justifyContent="space-between"
                                            alignItems="center"
                                            onClick={() => {
                                                if (onItemClick) onItemClick(id);
                                            }}
                                            spacing={1}
                                            padding={'10px'}
                                        >
                                            <div style={{width: '100%'}}><b>Дата приёма:</b> {info.split(" ")[1]}</div>
                                            <div style={{width: '100%'}}><b>Время приёма:</b> {info.split(" ")[2]}</div>
                                            <div style={{width: '100%'}}>
                                                <b>Врач:</b> {info.split(" ")[3] + " " + info.split(" ")[4] + " " + info.split(" ")[5] + " - " + info.split(" ")[7]}
                                            </div>
                                            <div style={{width: '100%'}}>
                                                <b>Пациент:</b> {info.split(" ")[8] + " " + info.split(" ")[9] + " " + info.split(" ")[10] + " - " + info.split(" ")[12] + " " + info.split(" ")[13] + " " + info.split(" ")[14] + " " + info.split(" ")[15]}
                                            </div>
                                            {info?.split(" ")[16] &&
                                                <div style={{width: '100%'}}><b>Дата выписки:</b> {info.split(" ")[16]}
                                                </div>}
                                            {!info?.split(" ")[16] &&
                                                <div style={{width: '100%'}}><b>Дата выписки:</b> Не закрыт</div>}
                                        </Stack>
                                    </Box>
                                }
                            </>
                        ))}
                    </Stack>
                </Box>
            )
        else
            return (
                <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                    <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                         alignItems={'center'}>
                        <h2>{title}</h2>
                        <Button
                            size={'small'}
                            style={{height: '40px', marginLeft: '15px'}}
                            variant="contained"
                            color="secondary"
                            disabled={!onCreate}
                            onClick={onCreate}
                        >
                            Назначить приём
                        </Button>
                    </Box>
                    {!data?.length && <div>Пусто</div>}
                    <Stack gap={2} width={'100%'}>
                        {data?.map(({info, id}) => (
                            <div>
                                <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                     sx={{backgroundColor: '#e6e6e6'}}>
                                    <Stack
                                        direction="column"
                                        justifyContent="space-between"
                                        alignItems="center"
                                        spacing={1}
                                        padding={'10px'}
                                    >
                                        <div style={{width: '100%'}}><b>Дата приёма:</b> {info.split(" ")[1]}</div>
                                        <div style={{width: '100%'}}><b>Время приёма:</b> {info.split(" ")[2]}</div>
                                        <div style={{width: '100%'}}>
                                            <b>Врач:</b> {info.split(" ")[3] + " " + info.split(" ")[4] + " " + info.split(" ")[5] + " - " + info.split(" ")[7]}
                                        </div>
                                        <div style={{width: '100%'}}>
                                            <b>Пациент:</b> {info.split(" ")[8] + " " + info.split(" ")[9] + " " + info.split(" ")[10] + " - " + info.split(" ")[12] + " " + info.split(" ")[13] + " " + info.split(" ")[14] + " " + info.split(" ")[15]}
                                        </div>
                                        {info?.split(" ")[16] &&
                                            <div style={{width: '100%'}}><b>Дата выписки:</b> {info.split(" ")[16]}
                                            </div>}
                                        {!info?.split(" ")[16] &&
                                            <div style={{width: '100%'}}><b>Дата выписки:</b> Не закрыт</div>}
                                        <Stack justifyContent="center" direction="row" gap="16px"
                                               className="edit-buttons">
                                            <IconButton
                                                onClick={(e: React.MouseEvent) => {
                                                    e.stopPropagation();
                                                    if (onEditItem) onEditItem(id);
                                                }}
                                            >
                                                <Edit/>
                                            </IconButton>

                                            <IconButton
                                                onClick={(e: React.MouseEvent) => {
                                                    e.stopPropagation();
                                                    if (onDeleteItem) onDeleteItem(id);
                                                }}
                                            >
                                                <Delete/>
                                            </IconButton>
                                        </Stack>
                                    </Stack>
                                </Box>
                            </div>
                        ))}
                    </Stack>
                </Box>
            )
    } else if(title == 'Анализы и их результаты') {
        return (
            <>
            <h2>{title}</h2>
                {   !date_of_extract &&
                    <Button
                        size={'small'}
                        style={{height: '40px', marginBottom: '15px'}}
                        variant="contained"
                        color="secondary"
                        onClick={onCreate}
                    >
                        Назначить анализ
                    </Button>}
            <Box display="flex" flexWrap="wrap" flexDirection="column" justifyContent="center" alignItems="center">
                {!analysisData?.length && <div>Пусто</div>}
                <Stack gap={2} width={'100%'}>
                    {analysisData?.map(({analysis, analysisResult, id}) => (
                        <>
                            <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                 sx={{backgroundColor: '#e6e6e6'}}>
                                <Stack
                                    direction="column"
                                    justifyContent="space-between"
                                    alignItems="center"
                                    spacing={1}
                                    padding={'10px'}
                                >
                                    <div style={{width: '100%'}}><b>Наименование анализа:</b> {analysis}</div>
                                    {analysisResult &&
                                        <>
                                            <div style={{alignItems: 'start', cursor: 'pointer', width: '100%'}}><b>Результат анализа:</b></div>
                                            <TextareaAutosize readOnly={true} className="TextAreaAnalysisResultStyle" minRows={1} defaultValue={analysisResult}></TextareaAutosize>
                                        </>}
                                    {!analysisResult &&
                                        <div style={{width: '100%'}}><b>Результат анализа:</b> Отсутствует</div>}
                                </Stack>
                                {   !date_of_extract &&
                                    <Stack justifyContent="center" direction="row" gap="16px" className="edit-buttons">
                                        <IconButton
                                            onClick={(e: React.MouseEvent) => {
                                                e.stopPropagation();
                                                if (onEditItem) onEditItem(id);
                                            }}
                                        >
                                            <Edit/>
                                        </IconButton>

                                        <IconButton
                                            onClick={(e: React.MouseEvent) => {
                                                e.stopPropagation();
                                                if (onDeleteItem) onDeleteItem(id);
                                            }}
                                        >
                                            <Delete/>
                                        </IconButton>
                                    </Stack>
                                }
                            </Box>
                        </>
                    ))}
                </Stack>
            </Box>
            </>
        )
    } else if(title == 'Медикаменты') {
        return (
            <>
                <h2>{title}</h2>
                {   !date_of_extract &&
                    <Button
                        size={'small'}
                        style={{height: '40px', marginBottom: '15px'}}
                        variant="contained"
                        color="secondary"
                        onClick={onCreate}
                    >
                        Назначить медикамент
                    </Button>
                }
                <Box display="flex" flexWrap="wrap" flexDirection="row" justifyContent="center" alignItems="center">
                    {!data?.length && <div>Пусто</div>}
                    <Stack gap={2} width={'100%'}>
                        {data?.map(({info, id}) => (
                            <>
                                <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                     sx={{backgroundColor: '#e6e6e6'}}>
                                    <Stack
                                        direction="row"
                                        justifyContent="space-between"
                                        alignItems="center"
                                        spacing={1}
                                        padding={'10px'}
                                    >
                                        <div style={{width: '100%'}}><b>Наименование медикамента:</b> {info} </div>
                                        {   !date_of_extract &&
                                            <span><Stack justifyContent="center" direction="row" gap="16px" className="edit-buttons">
                                                <IconButton
                                                    onClick={(e: React.MouseEvent) => {
                                                        e.stopPropagation();
                                                        if (onEditItem) onEditItem(id);
                                                    }}
                                                >
                                                    <Edit/>
                                                </IconButton>

                                                <IconButton
                                                    onClick={(e: React.MouseEvent) => {
                                                        e.stopPropagation();
                                                        if (onDeleteItem) onDeleteItem(id);
                                                    }}
                                                >
                                                    <Delete/>
                                                </IconButton>
                                            </Stack></span>
                                        }
                                    </Stack>

                                </Box>
                            </>
                        ))}
                    </Stack>
                </Box>
            </>
        )
    }   else if(title == 'Процедуры') {
        return (
            <>
                <h2>{title}</h2>
                {   !date_of_extract &&
                    <Button
                        size={'small'}
                        style={{height: '40px', marginBottom: '15px'}}
                        variant="contained"
                        color="secondary"
                        onClick={onCreate}
                    >
                        Назначить процедуру
                    </Button>
                }
                <Box display="flex" flexWrap="wrap" flexDirection="row" justifyContent="center" alignItems="center">
                    {!data?.length && <div>Пусто</div>}
                    <Stack gap={2} width={'100%'}>
                        {data?.map(({info, id}) => (
                            <>
                                <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                     sx={{backgroundColor: '#e6e6e6'}}>
                                    <Stack
                                        direction="row"
                                        justifyContent="space-between"
                                        alignItems="center"
                                        spacing={1}
                                        padding={'10px'}
                                    >
                                        <div style={{width: '100%'}}><b>Процедура:</b> {info} </div>
                                        {   !date_of_extract &&
                                            <span><Stack justifyContent="center" direction="row" gap="16px" className="edit-buttons">
                                                <IconButton
                                                    onClick={(e: React.MouseEvent) => {
                                                        e.stopPropagation();
                                                        if (onEditItem) onEditItem(id);
                                                    }}
                                                >
                                                    <Edit/>
                                                </IconButton>

                                                <IconButton
                                                    onClick={(e: React.MouseEvent) => {
                                                        e.stopPropagation();
                                                        if (onDeleteItem) onDeleteItem(id);
                                                    }}
                                                >
                                                    <Delete/>
                                                </IconButton>
                                            </Stack></span>
                                        }
                                    </Stack>

                                </Box>
                            </>
                        ))}
                    </Stack>
                </Box>
            </>
        )
    }
    else {
          return (
              <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                  <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start" alignItems={'center'}>
                      <h2>{title}</h2>
                      <Button
                          size={'small'}
                          style={{ height: '40px', marginLeft: '15px' }}
                          variant="contained"
                          color="secondary"
                          disabled={!onCreate}
                          onClick={onCreate}
                      >
                          Создать
                      </Button>
                  </Box>
                  {!data?.length && <div>Пусто</div>}
                  <Stack gap={2} width={'100%'}>
                      {data?.map(({ info, id }) => (
                          <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'} sx={{ backgroundColor: '#e6e6e6' }}>
                              <Stack
                                  direction="row"
                                  justifyContent="space-between"
                                  alignItems="center"
                                  onClick={() => {
                                      if (onItemClick) onItemClick(id);
                                  }}
                                  spacing={1}
                                  padding={'10px'}
                              >
                                  <div style={{ width: '100%' }}>{info}</div>

                                  <Stack justifyContent="center" direction="row" gap="16px" className="edit-buttons">
                                      <IconButton
                                          onClick={(e: React.MouseEvent) => {
                                              e.stopPropagation();
                                              if (onEditItem) onEditItem(id);
                                          }}
                                      >
                                          <Edit />
                                      </IconButton>

                                      <IconButton
                                          onClick={(e: React.MouseEvent) => {
                                              e.stopPropagation();
                                              if (onDeleteItem) onDeleteItem(id);
                                          }}
                                      >
                                          <Delete />
                                      </IconButton>
                                  </Stack>
                              </Stack>
                          </Box>
                      ))}
                  </Stack>
              </Box>
          );
      }
};

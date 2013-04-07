CREATE TABLE Metadata (
  idMetadata SERIAL  NOT NULL ,
  Name VARCHAR    ,
  Description TEXT    ,
  Hoover VARCHAR    ,
  Obligation BOOL      ,
PRIMARY KEY(idMetadata));

CREATE TABLE MetaConcerns (
  idMetadata SERIAL  NOT NULL ,
  concerns VARCHAR NOT NULL ,
  Obligation BOOL      ,
PRIMARY KEY(idMetadata,concerns)  ,
  FOREIGN KEY(idMetadata)
    REFERENCES Metadata(idMetadata));


CREATE TABLE Format (
  idFormat SERIAL  NOT NULL ,
  Nom VARCHAR    ,
  Extension VARCHAR      ,
PRIMARY KEY(idFormat));


CREATE TABLE Resolution (
  idResolution SERIAL  NOT NULL ,
  Nom VARCHAR    ,
  Description TEXT      ,
PRIMARY KEY(idResolution));


CREATE TABLE Rights (
  idRights SERIAL  NOT NULL ,
  Description VARCHAR    ,
  Name VARCHAR      ,
PRIMARY KEY(idRights));


CREATE TABLE Type (
  idType SERIAL  NOT NULL ,
  Name VARCHAR    ,
  Description TEXT      ,
PRIMARY KEY(idType));


CREATE TABLE Workflow (
  idWorkflow SERIAL  NOT NULL ,
  Name VARCHAR    ,
  Description VARCHAR      ,
PRIMARY KEY(idWorkflow));


CREATE TABLE Rule (
  idRule SERIAL  NOT NULL ,
  Name VARCHAR    ,
  Description VARCHAR      ,
PRIMARY KEY(idRule));


CREATE TABLE Role (
  idRole SERIAL  NOT NULL ,
  Nom VARCHAR    ,
  Authlvl INTEGER      ,
PRIMARY KEY(idRole));


CREATE TABLE Site (
  idSite SERIAL  NOT NULL ,
  Rule INTEGER   NOT NULL   ,
PRIMARY KEY(idSite)  ,
  FOREIGN KEY(Rule)
    REFERENCES Rule(idRule));

CREATE INDEX SFK_Rule ON Site (Rule);

CREATE TABLE Wanda_User (
  Certificate SERIAL  NOT NULL ,
  Role INTEGER   NOT NULL ,
  Name VARCHAR    ,
  Forename VARCHAR    ,
  Creator INTEGER    ,
  Mail VARCHAR      ,
PRIMARY KEY(Certificate)  ,
  FOREIGN KEY(Role)
    REFERENCES Role(idRole),
  FOREIGN KEY (Creator)
    REFERENCES Wanda_User(Certificate));

CREATE INDEX WUFK_Role ON Wanda_User(Role);


CREATE TABLE SiteMeta (
  Site INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Site, Metadata)    ,
  FOREIGN KEY(Site)
    REFERENCES Site(idSite),
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata));


CREATE INDEX SMFK_Site ON SiteMeta (Site);
CREATE INDEX SMFK_Meta ON SiteMeta (Metadata);

CREATE TABLE Annotation (
  idAnnotation SERIAL  NOT NULL ,
  Link VARCHAR NOT NULL,
  Workflow INTEGER   NOT NULL ,
  Owner INTEGER   NOT NULL ,
  Containername VARCHAR,
  Container INTEGER   NOT NULL   ,
PRIMARY KEY(idAnnotation)      ,
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Workflow)
    REFERENCES Workflow(idWorkflow));


CREATE INDEX AFK_Owner ON Annotation (Owner);
CREATE INDEX AFK_Workflow ON Annotation (Workflow);
CREATE INDEX AFK_LinkKey ON Annotation (LinkKey);



CREATE TABLE Corpus (
  idCorpus SERIAL  NOT NULL ,
  Rule INTEGER   NOT NULL ,
  Container INTEGER   NOT NULL ,
  Owner INTEGER   NOT NULL   ,
PRIMARY KEY(idCorpus)      ,
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Container)
    REFERENCES Site(idSite),
  FOREIGN KEY(Rule)
    REFERENCES Rule(idRule));


CREATE INDEX CFK_Creator ON Corpus (Owner);
CREATE INDEX CFK_Site ON Corpus (Site);
CREATE INDEX CFK_Rule ON Corpus (Rule);


CREATE TABLE UserCorpusAccess (
  Wanda_User INTEGER   NOT NULL ,
  Corpus INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL   ,
PRIMARY KEY(Wanda_User, Corpus)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Corpus)
    REFERENCES Corpus(idCorpus),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX UCAFK_Wanda_User ON UserCorpusAccess (Wanda_User);
CREATE INDEX UCAFK_Corpus ON UserCorpusAccess (Corpus);
CREATE INDEX UCAFK_Rights ON UserCorpusAccess (Rights);


CREATE TABLE UserAnnotationAccess (
  Wanda_User INTEGER   NOT NULL ,
  Annotation INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL ,
  LastAcess DATE      ,
PRIMARY KEY(Wanda_User, Annotation)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Annotation)
    REFERENCES Annotation(idAnnotation),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX UAAFK_Wanda_User ON UserAnnotationAccess (Wanda_User);
CREATE INDEX UAAFK_Annotation ON UserAnnotationAccess (Annotation);
CREATE INDEX UAAFK_Rights ON UserAnnotationAccess (Rights);



CREATE TABLE Session (
  idSession SERIAL  NOT NULL ,
  Rule INTEGER   NOT NULL ,
  Corpus INTEGER   NOT NULL ,
  Owner INTEGER   NOT NULL   ,
PRIMARY KEY(idSession)      ,
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Corpus)
    REFERENCES Corpus(idCorpus),
  FOREIGN KEY(Rule)
    REFERENCES Rule(idRule));


CREATE INDEX SSFK_Creator ON Session (Owner);
CREATE INDEX SSFK_Corpus ON Session (Corpus);
CREATE INDEX SSFK_Rule ON Session (Rule);




CREATE TABLE UserSiteAccess (
  Wanda_User INTEGER   NOT NULL ,
  Site INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL   ,
PRIMARY KEY(Wanda_User, Site)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Site)
    REFERENCES Site(idSite),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX USAFK_Wanda_User ON UserSiteAccess (Wanda_User);
CREATE INDEX USAFK_Site ON UserSiteAccess (Site);
CREATE INDEX USAFK_Rights ON UserSiteAccess (Rights);



CREATE TABLE UserSessionAccess (
  Wanda_User INTEGER   NOT NULL ,
  Session INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL   ,
PRIMARY KEY(Wanda_User, Session)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Session)
    REFERENCES Session(idSession),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX USSAFK_Wanda_User ON UserSessionAccess (Wanda_User);
CREATE INDEX USSAFK_Session ON UserSessionAccess (Session);
CREATE INDEX USSAFK_Rights ON UserSessionAccess (Rights);



CREATE TABLE View (
  idView SERIAL  NOT NULL ,
  Type INTEGER   NOT NULL ,
  Rule INTEGER   NOT NULL ,
  Owner INTEGER   NOT NULL ,
  Session INTEGER   NOT NULL   ,
PRIMARY KEY(idView)        ,
  FOREIGN KEY(Session)
    REFERENCES Session(idSession),
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Rule)
    REFERENCES Rule(idRule),
  FOREIGN KEY(Type)
    REFERENCES Type(idType));


CREATE INDEX VWFK_Session ON View (Session);
CREATE INDEX VWFK_Owner ON View (Owner);
CREATE INDEX VWFK_Rule ON View (Rule);
CREATE INDEX VWFK_Type ON View (Type);



CREATE TABLE Montage (
  idMontage SERIAL  NOT NULL ,
  Owner INTEGER   NOT NULL   ,
PRIMARY KEY(idMontage)  ,
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate));


CREATE INDEX MFK_Owner ON Montage (Owner);



CREATE TABLE ViewMeta (
  View INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(View, Metadata)    ,
  FOREIGN KEY(View)
    REFERENCES View(idView),
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata));


CREATE INDEX VWMFK_View ON ViewMeta (View);
CREATE INDEX VWMFK_Meta ON ViewMeta (Metadata);


CREATE TABLE SubCorpus (
  Pere INTEGER   NOT NULL   ,
  Fils INTEGER	 NOT NULL   ,
PRIMARY KEY(Pere, Fils)    ,
  FOREIGN KEY(Pere)
    REFERENCES Corpus(idCorpus),
  FOREIGN KEY(Fils)
    REFERENCES Corpus(idCorpus));


CREATE INDEX SCFK_Pere ON SubCorpus (Pere);
CREATE INDEX SCFK_Fils ON SubCorpus (Fils);



CREATE TABLE SessionMeta (
  Session INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Session, Metadata)    ,
  FOREIGN KEY(Session)
    REFERENCES Session(idSession),
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata));


CREATE INDEX SSMFK_Session ON SessionMeta (Session);
CREATE INDEX SSMFK_Meta ON SessionMeta (Metadata);



CREATE TABLE CorpusMeta (
  Corpus INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Corpus, Metadata)    ,
  FOREIGN KEY(Corpus)
    REFERENCES Corpus(idCorpus),
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata));


CREATE INDEX CMFK_Corpus ON CorpusMeta (Corpus);
CREATE INDEX CMFK_Meta ON CorpusMeta (Metadata);


CREATE TABLE AnnotationMeta (
  Annotation INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Metadata, Annotation)    ,
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata),
  FOREIGN KEY(Annotation)
    REFERENCES Annotation(idAnnotation));


CREATE INDEX AMFK_Meta ON AnnotationMeta (Metadata);
CREATE INDEX AMFK_Annotation ON AnnotationMeta (Annotation);


CREATE TABLE MontageMeta (
  Montage INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Montage, Metadata)    ,
  FOREIGN KEY(Montage)
    REFERENCES Montage(idMontage),
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata));


CREATE INDEX MMFK_Montage ON MontageMeta (Montage);
CREATE INDEX MMFK_Metadata ON MontageMeta (Metadata);



CREATE TABLE MontageParts (
  Montage INTEGER   NOT NULL ,
  View INTEGER   NOT NULL   ,
PRIMARY KEY(Montage, View)    ,
  FOREIGN KEY(Montage)
    REFERENCES Montage(idMontage),
  FOREIGN KEY(View)
    REFERENCES View(idView));


CREATE INDEX MPFK_Montage ON MontageParts (Montage);
CREATE INDEX MPFK_View ON MontageParts (View);

CREATE TABLE UserViewAccess (
  Wanda_User INTEGER   NOT NULL ,
  View INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL   ,
PRIMARY KEY(Wanda_User, View)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(View)
    REFERENCES View(idView),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX UVWAFK_Wanda_User ON UserViewAccess (Wanda_User);
CREATE INDEX UVWAFK_View ON UserViewAccess (View);
CREATE INDEX UVWAFK_Rights ON UserViewAccess (Rights);


CREATE TABLE Video (
  idVideo SERIAL  NOT NULL ,
  Workflow INTEGER   NOT NULL ,
  View INTEGER   NOT NULL ,
  Owner INTEGER   NOT NULL   ,
PRIMARY KEY(idVideo)      ,
  FOREIGN KEY(Owner)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(View)
    REFERENCES View(idView),
  FOREIGN KEY(Workflow)
    REFERENCES Workflow(idWorkflow));


CREATE INDEX VFK_Owner ON Video (Owner);
CREATE INDEX VFK_View ON Video (View);
CREATE INDEX VFK_Workflow ON Video (Workflow);


CREATE TABLE UserVideoAccess (
  Wanda_User INTEGER   NOT NULL ,
  Video INTEGER   NOT NULL ,
  Rights INTEGER   NOT NULL ,
  LastAccess DATE      ,
PRIMARY KEY(Wanda_User, Video)      ,
  FOREIGN KEY(Wanda_User)
    REFERENCES Wanda_User(Certificate),
  FOREIGN KEY(Video)
    REFERENCES Video(idVideo),
  FOREIGN KEY(Rights)
    REFERENCES Rights(idRights));


CREATE INDEX UVAFK_Wanda_User ON UserVideoAccess (Wanda_User);
CREATE INDEX UVAFK_Video ON UserVideoAccess (Video);
CREATE INDEX UVAFK_Rights ON UserVideoAccess (Rights);


CREATE TABLE VideoMeta (
  Video INTEGER   NOT NULL ,
  Metadata INTEGER   NOT NULL ,
  Content TEXT      ,
PRIMARY KEY(Metadata, Video)    ,
  FOREIGN KEY(Metadata)
    REFERENCES Metadata(idMetadata),
  FOREIGN KEY(Video)
    REFERENCES Video(idVideo));


CREATE INDEX VMFK_Meta ON VideoMeta (Metadata);
CREATE INDEX VMFK_Video ON VideoMeta (Video);


CREATE TABLE ConcreteVideo (
  Video INTEGER   NOT NULL ,
  Resolution INTEGER   NOT NULL ,
  Format INTEGER   NOT NULL ,
  Link VARCHAR      ,
PRIMARY KEY(Video, Resolution, Format)      ,
  FOREIGN KEY(Video)
    REFERENCES Video(idVideo),
  FOREIGN KEY(Resolution)
    REFERENCES Resolution(idResolution),
  FOREIGN KEY(Format)
    REFERENCES Format(idFormat));


CREATE INDEX CVFK_Video ON ConcreteVideo (Video);
CREATE INDEX CVFK_Resolution ON ConcreteVideo (Resolution);
CREATE INDEX CVFK_Format ON ConcreteVideo (Format);





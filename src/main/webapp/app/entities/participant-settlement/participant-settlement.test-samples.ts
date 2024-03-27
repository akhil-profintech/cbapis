import { IParticipantSettlement, NewParticipantSettlement } from './participant-settlement.model';

export const sampleWithRequiredData: IParticipantSettlement = {
  id: 30996,
  participantId: 31289,
  participantName: 'in blind',
  gstId: 'consequently',
  settlementType: 'TPSettlement',
  chargeType: 'FPTransactionCharges',
  settlementAmount: 3761,
  settlementDate: 'barring',
  settlementDueDate: 'what bad',
  settlementContactInfo: 'athwart',
  patronOfPayment: 'around indeed after',
  recipientOfPayment: 'linkage recreation',
  settlementMethod: 'variable what feather',
  tenantId: 8130,
  accName: 'carve quietly solidly',
  ifscCode: 'suppression where',
  accNumber: 8374,
  docId: 'bah',
};

export const sampleWithPartialData: IParticipantSettlement = {
  id: 28022,
  participantId: 8384,
  participantName: 'excitedly meaty',
  gstId: 'peppery boohoo phooey',
  settlementType: 'PFTSettlement',
  chargeType: 'FPTransactionCharges',
  settlementAmount: 22797,
  settlementDate: 'provided almond chauffeur',
  settlementDueDate: 'back healthily',
  settlementContactInfo: 'ick into',
  patronOfPayment: 'disappoint budget connote',
  recipientOfPayment: 'dearly',
  settlementMethod: 'qua',
  tenantId: 4861,
  accName: 'psst for yum',
  ifscCode: 'recent',
  accNumber: 21530,
  docId: 'save even because',
  recordStatus: 'uh-huh frisk',
};

export const sampleWithFullData: IParticipantSettlement = {
  id: 23654,
  participantSettlementId: 5716,
  participantSettlementUlidId: 'defiantly blather',
  participantId: 12926,
  participantName: 'huzzah',
  gstId: 'till round',
  settlementType: 'PFTSettlement',
  chargeType: 'CRETransactionFees',
  settlementAmount: 190,
  settlementDate: 'international',
  settlementDueDate: 'aha',
  settlementContactInfo: 'ouch concerning tomorrow',
  patronOfPayment: 'ew cheap',
  recipientOfPayment: 'now ouch cabana',
  settlementMethod: 'plus so',
  tenantId: 21569,
  accName: 'suddenly',
  ifscCode: 'retirement circular wonderfully',
  accNumber: 32702,
  docId: 'icky',
  recordStatus: 'clearly quarterly salmon',
};

export const sampleWithNewData: NewParticipantSettlement = {
  participantId: 6300,
  participantName: 'what',
  gstId: 'silently because when',
  settlementType: 'TPSettlement',
  chargeType: 'CRETransactionFees',
  settlementAmount: 26881,
  settlementDate: 'unfortunately',
  settlementDueDate: 'gracefully',
  settlementContactInfo: 'glorious shiny',
  patronOfPayment: 'squiggly near equally',
  recipientOfPayment: 'ah if',
  settlementMethod: 'opposite nervously',
  tenantId: 8474,
  accName: 'charger um cloak',
  ifscCode: 'angrily yum ouch',
  accNumber: 24523,
  docId: 'obnoxiously',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

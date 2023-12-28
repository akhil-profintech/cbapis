import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'context',
    data: { pageTitle: 'cbapisApp.context.home.title' },
    loadChildren: () => import('./context/context.routes'),
  },
  {
    path: 'anchor-trader',
    data: { pageTitle: 'cbapisApp.anchorTrader.home.title' },
    loadChildren: () => import('./anchor-trader/anchor-trader.routes'),
  },
  {
    path: 'finance-partner',
    data: { pageTitle: 'cbapisApp.financePartner.home.title' },
    loadChildren: () => import('./finance-partner/finance-partner.routes'),
  },
  {
    path: 'trade-partner',
    data: { pageTitle: 'cbapisApp.tradePartner.home.title' },
    loadChildren: () => import('./trade-partner/trade-partner.routes'),
  },
  {
    path: 'credit-bazaar-integrator',
    data: { pageTitle: 'cbapisApp.creditBazaarIntegrator.home.title' },
    loadChildren: () => import('./credit-bazaar-integrator/credit-bazaar-integrator.routes'),
  },
  {
    path: 'placed-offer',
    data: { pageTitle: 'cbapisApp.placedOffer.home.title' },
    loadChildren: () => import('./placed-offer/placed-offer.routes'),
  },
  {
    path: 'finance-request',
    data: { pageTitle: 'cbapisApp.financeRequest.home.title' },
    loadChildren: () => import('./finance-request/finance-request.routes'),
  },
  {
    path: 'accepted-offer',
    data: { pageTitle: 'cbapisApp.acceptedOffer.home.title' },
    loadChildren: () => import('./accepted-offer/accepted-offer.routes'),
  },
  {
    path: 'disbursement',
    data: { pageTitle: 'cbapisApp.disbursement.home.title' },
    loadChildren: () => import('./disbursement/disbursement.routes'),
  },
  {
    path: 'repayment',
    data: { pageTitle: 'cbapisApp.repayment.home.title' },
    loadChildren: () => import('./repayment/repayment.routes'),
  },
  {
    path: 'credit-account-details',
    data: { pageTitle: 'cbapisApp.creditAccountDetails.home.title' },
    loadChildren: () => import('./credit-account-details/credit-account-details.routes'),
  },
  {
    path: 'escrow-account-details',
    data: { pageTitle: 'cbapisApp.escrowAccountDetails.home.title' },
    loadChildren: () => import('./escrow-account-details/escrow-account-details.routes'),
  },
  {
    path: 'ft-transaction-details',
    data: { pageTitle: 'cbapisApp.fTTransactionDetails.home.title' },
    loadChildren: () => import('./ft-transaction-details/ft-transaction-details.routes'),
  },
  {
    path: 'collection-transaction-details',
    data: { pageTitle: 'cbapisApp.collectionTransactionDetails.home.title' },
    loadChildren: () => import('./collection-transaction-details/collection-transaction-details.routes'),
  },
  {
    path: 'doc-details',
    data: { pageTitle: 'cbapisApp.docDetails.home.title' },
    loadChildren: () => import('./doc-details/doc-details.routes'),
  },
  {
    path: 'prospect-request',
    data: { pageTitle: 'cbapisApp.prospectRequest.home.title' },
    loadChildren: () => import('./prospect-request/prospect-request.routes'),
  },
  {
    path: 'request-offer',
    data: { pageTitle: 'cbapisApp.requestOffer.home.title' },
    loadChildren: () => import('./request-offer/request-offer.routes'),
  },
  {
    path: 'trade',
    data: { pageTitle: 'cbapisApp.trade.home.title' },
    loadChildren: () => import('./trade/trade.routes'),
  },
  {
    path: 'settlement',
    data: { pageTitle: 'cbapisApp.settlement.home.title' },
    loadChildren: () => import('./settlement/settlement.routes'),
  },
  {
    path: 'participant-settlement',
    data: { pageTitle: 'cbapisApp.participantSettlement.home.title' },
    loadChildren: () => import('./participant-settlement/participant-settlement.routes'),
  },
  {
    path: 'update-va',
    data: { pageTitle: 'cbapisApp.updateVA.home.title' },
    loadChildren: () => import('./update-va/update-va.routes'),
  },
  {
    path: 'funds-transfer',
    data: { pageTitle: 'cbapisApp.fundsTransfer.home.title' },
    loadChildren: () => import('./funds-transfer/funds-transfer.routes'),
  },
  {
    path: 'insta-alert',
    data: { pageTitle: 'cbapisApp.instaAlert.home.title' },
    loadChildren: () => import('./insta-alert/insta-alert.routes'),
  },
  {
    path: 'bene-validation',
    data: { pageTitle: 'cbapisApp.beneValidation.home.title' },
    loadChildren: () => import('./bene-validation/bene-validation.routes'),
  },
  {
    path: 'va-number',
    data: { pageTitle: 'cbapisApp.vANumber.home.title' },
    loadChildren: () => import('./va-number/va-number.routes'),
  },
  {
    path: 'trade-entity',
    data: { pageTitle: 'cbapisApp.tradeEntity.home.title' },
    loadChildren: () => import('./trade-entity/trade-entity.routes'),
  },
  {
    path: 'cbcre-process',
    data: { pageTitle: 'cbapisApp.cBCREProcess.home.title' },
    loadChildren: () => import('./cbcre-process/cbcre-process.routes'),
  },
  {
    path: 'individual-assessment',
    data: { pageTitle: 'cbapisApp.individualAssessment.home.title' },
    loadChildren: () => import('./individual-assessment/individual-assessment.routes'),
  },
  {
    path: 'cre-highlights',
    data: { pageTitle: 'cbapisApp.cREHighlights.home.title' },
    loadChildren: () => import('./cre-highlights/cre-highlights.routes'),
  },
  {
    path: 'cre-observations',
    data: { pageTitle: 'cbapisApp.cREObservations.home.title' },
    loadChildren: () => import('./cre-observations/cre-observations.routes'),
  },
  {
    path: 'action',
    data: { pageTitle: 'cbapisApp.action.home.title' },
    loadChildren: () => import('./action/action.routes'),
  },
  {
    path: 'client-codes',
    data: { pageTitle: 'cbapisApp.clientCodes.home.title' },
    loadChildren: () => import('./client-codes/client-codes.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;

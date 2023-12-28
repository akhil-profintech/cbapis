import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { DisbursementService } from 'app/entities/disbursement/service/disbursement.service';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { RepaymentService } from 'app/entities/repayment/service/repayment.service';
import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { ParticipantSettlementService } from 'app/entities/participant-settlement/service/participant-settlement.service';
import { IDocDetails } from '../doc-details.model';
import { DocDetailsService } from '../service/doc-details.service';
import { DocDetailsFormService } from './doc-details-form.service';

import { DocDetailsUpdateComponent } from './doc-details-update.component';

describe('DocDetails Management Update Component', () => {
  let comp: DocDetailsUpdateComponent;
  let fixture: ComponentFixture<DocDetailsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let docDetailsFormService: DocDetailsFormService;
  let docDetailsService: DocDetailsService;
  let disbursementService: DisbursementService;
  let repaymentService: RepaymentService;
  let participantSettlementService: ParticipantSettlementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), DocDetailsUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DocDetailsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DocDetailsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    docDetailsFormService = TestBed.inject(DocDetailsFormService);
    docDetailsService = TestBed.inject(DocDetailsService);
    disbursementService = TestBed.inject(DisbursementService);
    repaymentService = TestBed.inject(RepaymentService);
    participantSettlementService = TestBed.inject(ParticipantSettlementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Disbursement query and add missing value', () => {
      const docDetails: IDocDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 18004 };
      docDetails.disbursement = disbursement;

      const disbursementCollection: IDisbursement[] = [{ id: 5042 }];
      jest.spyOn(disbursementService, 'query').mockReturnValue(of(new HttpResponse({ body: disbursementCollection })));
      const additionalDisbursements = [disbursement];
      const expectedCollection: IDisbursement[] = [...additionalDisbursements, ...disbursementCollection];
      jest.spyOn(disbursementService, 'addDisbursementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(disbursementService.query).toHaveBeenCalled();
      expect(disbursementService.addDisbursementToCollectionIfMissing).toHaveBeenCalledWith(
        disbursementCollection,
        ...additionalDisbursements.map(expect.objectContaining),
      );
      expect(comp.disbursementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Repayment query and add missing value', () => {
      const docDetails: IDocDetails = { id: 456 };
      const repayment: IRepayment = { id: 30235 };
      docDetails.repayment = repayment;

      const repaymentCollection: IRepayment[] = [{ id: 7734 }];
      jest.spyOn(repaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: repaymentCollection })));
      const additionalRepayments = [repayment];
      const expectedCollection: IRepayment[] = [...additionalRepayments, ...repaymentCollection];
      jest.spyOn(repaymentService, 'addRepaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(repaymentService.query).toHaveBeenCalled();
      expect(repaymentService.addRepaymentToCollectionIfMissing).toHaveBeenCalledWith(
        repaymentCollection,
        ...additionalRepayments.map(expect.objectContaining),
      );
      expect(comp.repaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call ParticipantSettlement query and add missing value', () => {
      const docDetails: IDocDetails = { id: 456 };
      const participantsettlement: IParticipantSettlement = { id: 27864 };
      docDetails.participantsettlement = participantsettlement;

      const participantSettlementCollection: IParticipantSettlement[] = [{ id: 25263 }];
      jest.spyOn(participantSettlementService, 'query').mockReturnValue(of(new HttpResponse({ body: participantSettlementCollection })));
      const additionalParticipantSettlements = [participantsettlement];
      const expectedCollection: IParticipantSettlement[] = [...additionalParticipantSettlements, ...participantSettlementCollection];
      jest.spyOn(participantSettlementService, 'addParticipantSettlementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(participantSettlementService.query).toHaveBeenCalled();
      expect(participantSettlementService.addParticipantSettlementToCollectionIfMissing).toHaveBeenCalledWith(
        participantSettlementCollection,
        ...additionalParticipantSettlements.map(expect.objectContaining),
      );
      expect(comp.participantSettlementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const docDetails: IDocDetails = { id: 456 };
      const disbursement: IDisbursement = { id: 1868 };
      docDetails.disbursement = disbursement;
      const repayment: IRepayment = { id: 11427 };
      docDetails.repayment = repayment;
      const participantsettlement: IParticipantSettlement = { id: 8286 };
      docDetails.participantsettlement = participantsettlement;

      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      expect(comp.disbursementsSharedCollection).toContain(disbursement);
      expect(comp.repaymentsSharedCollection).toContain(repayment);
      expect(comp.participantSettlementsSharedCollection).toContain(participantsettlement);
      expect(comp.docDetails).toEqual(docDetails);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsFormService, 'getDocDetails').mockReturnValue(docDetails);
      jest.spyOn(docDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: docDetails }));
      saveSubject.complete();

      // THEN
      expect(docDetailsFormService.getDocDetails).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(docDetailsService.update).toHaveBeenCalledWith(expect.objectContaining(docDetails));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsFormService, 'getDocDetails').mockReturnValue({ id: null });
      jest.spyOn(docDetailsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: docDetails }));
      saveSubject.complete();

      // THEN
      expect(docDetailsFormService.getDocDetails).toHaveBeenCalled();
      expect(docDetailsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocDetails>>();
      const docDetails = { id: 123 };
      jest.spyOn(docDetailsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ docDetails });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(docDetailsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDisbursement', () => {
      it('Should forward to disbursementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(disbursementService, 'compareDisbursement');
        comp.compareDisbursement(entity, entity2);
        expect(disbursementService.compareDisbursement).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRepayment', () => {
      it('Should forward to repaymentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(repaymentService, 'compareRepayment');
        comp.compareRepayment(entity, entity2);
        expect(repaymentService.compareRepayment).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareParticipantSettlement', () => {
      it('Should forward to participantSettlementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(participantSettlementService, 'compareParticipantSettlement');
        comp.compareParticipantSettlement(entity, entity2);
        expect(participantSettlementService.compareParticipantSettlement).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

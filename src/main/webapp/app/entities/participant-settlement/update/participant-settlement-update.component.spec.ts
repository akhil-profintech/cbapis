import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ISettlement } from 'app/entities/settlement/settlement.model';
import { SettlementService } from 'app/entities/settlement/service/settlement.service';
import { ParticipantSettlementService } from '../service/participant-settlement.service';
import { IParticipantSettlement } from '../participant-settlement.model';
import { ParticipantSettlementFormService } from './participant-settlement-form.service';

import { ParticipantSettlementUpdateComponent } from './participant-settlement-update.component';

describe('ParticipantSettlement Management Update Component', () => {
  let comp: ParticipantSettlementUpdateComponent;
  let fixture: ComponentFixture<ParticipantSettlementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let participantSettlementFormService: ParticipantSettlementFormService;
  let participantSettlementService: ParticipantSettlementService;
  let settlementService: SettlementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ParticipantSettlementUpdateComponent],
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
      .overrideTemplate(ParticipantSettlementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ParticipantSettlementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    participantSettlementFormService = TestBed.inject(ParticipantSettlementFormService);
    participantSettlementService = TestBed.inject(ParticipantSettlementService);
    settlementService = TestBed.inject(SettlementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Settlement query and add missing value', () => {
      const participantSettlement: IParticipantSettlement = { id: 456 };
      const settlement: ISettlement = { id: 32218 };
      participantSettlement.settlement = settlement;

      const settlementCollection: ISettlement[] = [{ id: 17344 }];
      jest.spyOn(settlementService, 'query').mockReturnValue(of(new HttpResponse({ body: settlementCollection })));
      const additionalSettlements = [settlement];
      const expectedCollection: ISettlement[] = [...additionalSettlements, ...settlementCollection];
      jest.spyOn(settlementService, 'addSettlementToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ participantSettlement });
      comp.ngOnInit();

      expect(settlementService.query).toHaveBeenCalled();
      expect(settlementService.addSettlementToCollectionIfMissing).toHaveBeenCalledWith(
        settlementCollection,
        ...additionalSettlements.map(expect.objectContaining),
      );
      expect(comp.settlementsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const participantSettlement: IParticipantSettlement = { id: 456 };
      const settlement: ISettlement = { id: 3241 };
      participantSettlement.settlement = settlement;

      activatedRoute.data = of({ participantSettlement });
      comp.ngOnInit();

      expect(comp.settlementsSharedCollection).toContain(settlement);
      expect(comp.participantSettlement).toEqual(participantSettlement);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParticipantSettlement>>();
      const participantSettlement = { id: 123 };
      jest.spyOn(participantSettlementFormService, 'getParticipantSettlement').mockReturnValue(participantSettlement);
      jest.spyOn(participantSettlementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ participantSettlement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: participantSettlement }));
      saveSubject.complete();

      // THEN
      expect(participantSettlementFormService.getParticipantSettlement).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(participantSettlementService.update).toHaveBeenCalledWith(expect.objectContaining(participantSettlement));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParticipantSettlement>>();
      const participantSettlement = { id: 123 };
      jest.spyOn(participantSettlementFormService, 'getParticipantSettlement').mockReturnValue({ id: null });
      jest.spyOn(participantSettlementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ participantSettlement: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: participantSettlement }));
      saveSubject.complete();

      // THEN
      expect(participantSettlementFormService.getParticipantSettlement).toHaveBeenCalled();
      expect(participantSettlementService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParticipantSettlement>>();
      const participantSettlement = { id: 123 };
      jest.spyOn(participantSettlementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ participantSettlement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(participantSettlementService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareSettlement', () => {
      it('Should forward to settlementService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(settlementService, 'compareSettlement');
        comp.compareSettlement(entity, entity2);
        expect(settlementService.compareSettlement).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

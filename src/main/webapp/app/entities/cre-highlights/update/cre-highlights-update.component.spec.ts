import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { IIndividualAssessment } from 'app/entities/individual-assessment/individual-assessment.model';
import { IndividualAssessmentService } from 'app/entities/individual-assessment/service/individual-assessment.service';
import { ICREHighlights } from '../cre-highlights.model';
import { CREHighlightsService } from '../service/cre-highlights.service';
import { CREHighlightsFormService } from './cre-highlights-form.service';

import { CREHighlightsUpdateComponent } from './cre-highlights-update.component';

describe('CREHighlights Management Update Component', () => {
  let comp: CREHighlightsUpdateComponent;
  let fixture: ComponentFixture<CREHighlightsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cREHighlightsFormService: CREHighlightsFormService;
  let cREHighlightsService: CREHighlightsService;
  let cBCREProcessService: CBCREProcessService;
  let individualAssessmentService: IndividualAssessmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CREHighlightsUpdateComponent],
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
      .overrideTemplate(CREHighlightsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CREHighlightsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cREHighlightsFormService = TestBed.inject(CREHighlightsFormService);
    cREHighlightsService = TestBed.inject(CREHighlightsService);
    cBCREProcessService = TestBed.inject(CBCREProcessService);
    individualAssessmentService = TestBed.inject(IndividualAssessmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call CBCREProcess query and add missing value', () => {
      const cREHighlights: ICREHighlights = { id: 456 };
      const cbcreprocess: ICBCREProcess = { id: 12306 };
      cREHighlights.cbcreprocess = cbcreprocess;

      const cBCREProcessCollection: ICBCREProcess[] = [{ id: 3933 }];
      jest.spyOn(cBCREProcessService, 'query').mockReturnValue(of(new HttpResponse({ body: cBCREProcessCollection })));
      const additionalCBCREProcesses = [cbcreprocess];
      const expectedCollection: ICBCREProcess[] = [...additionalCBCREProcesses, ...cBCREProcessCollection];
      jest.spyOn(cBCREProcessService, 'addCBCREProcessToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cREHighlights });
      comp.ngOnInit();

      expect(cBCREProcessService.query).toHaveBeenCalled();
      expect(cBCREProcessService.addCBCREProcessToCollectionIfMissing).toHaveBeenCalledWith(
        cBCREProcessCollection,
        ...additionalCBCREProcesses.map(expect.objectContaining),
      );
      expect(comp.cBCREProcessesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call IndividualAssessment query and add missing value', () => {
      const cREHighlights: ICREHighlights = { id: 456 };
      const individualassessment: IIndividualAssessment = { id: 19398 };
      cREHighlights.individualassessment = individualassessment;

      const individualAssessmentCollection: IIndividualAssessment[] = [{ id: 11142 }];
      jest.spyOn(individualAssessmentService, 'query').mockReturnValue(of(new HttpResponse({ body: individualAssessmentCollection })));
      const additionalIndividualAssessments = [individualassessment];
      const expectedCollection: IIndividualAssessment[] = [...additionalIndividualAssessments, ...individualAssessmentCollection];
      jest.spyOn(individualAssessmentService, 'addIndividualAssessmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cREHighlights });
      comp.ngOnInit();

      expect(individualAssessmentService.query).toHaveBeenCalled();
      expect(individualAssessmentService.addIndividualAssessmentToCollectionIfMissing).toHaveBeenCalledWith(
        individualAssessmentCollection,
        ...additionalIndividualAssessments.map(expect.objectContaining),
      );
      expect(comp.individualAssessmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cREHighlights: ICREHighlights = { id: 456 };
      const cbcreprocess: ICBCREProcess = { id: 7183 };
      cREHighlights.cbcreprocess = cbcreprocess;
      const individualassessment: IIndividualAssessment = { id: 30245 };
      cREHighlights.individualassessment = individualassessment;

      activatedRoute.data = of({ cREHighlights });
      comp.ngOnInit();

      expect(comp.cBCREProcessesSharedCollection).toContain(cbcreprocess);
      expect(comp.individualAssessmentsSharedCollection).toContain(individualassessment);
      expect(comp.cREHighlights).toEqual(cREHighlights);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREHighlights>>();
      const cREHighlights = { id: 123 };
      jest.spyOn(cREHighlightsFormService, 'getCREHighlights').mockReturnValue(cREHighlights);
      jest.spyOn(cREHighlightsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREHighlights });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cREHighlights }));
      saveSubject.complete();

      // THEN
      expect(cREHighlightsFormService.getCREHighlights).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cREHighlightsService.update).toHaveBeenCalledWith(expect.objectContaining(cREHighlights));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREHighlights>>();
      const cREHighlights = { id: 123 };
      jest.spyOn(cREHighlightsFormService, 'getCREHighlights').mockReturnValue({ id: null });
      jest.spyOn(cREHighlightsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREHighlights: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cREHighlights }));
      saveSubject.complete();

      // THEN
      expect(cREHighlightsFormService.getCREHighlights).toHaveBeenCalled();
      expect(cREHighlightsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICREHighlights>>();
      const cREHighlights = { id: 123 };
      jest.spyOn(cREHighlightsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cREHighlights });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cREHighlightsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCBCREProcess', () => {
      it('Should forward to cBCREProcessService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(cBCREProcessService, 'compareCBCREProcess');
        comp.compareCBCREProcess(entity, entity2);
        expect(cBCREProcessService.compareCBCREProcess).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareIndividualAssessment', () => {
      it('Should forward to individualAssessmentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(individualAssessmentService, 'compareIndividualAssessment');
        comp.compareIndividualAssessment(entity, entity2);
        expect(individualAssessmentService.compareIndividualAssessment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

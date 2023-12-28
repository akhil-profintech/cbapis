import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ICBCREProcess } from 'app/entities/cbcre-process/cbcre-process.model';
import { CBCREProcessService } from 'app/entities/cbcre-process/service/cbcre-process.service';
import { IndividualAssessmentService } from '../service/individual-assessment.service';
import { IIndividualAssessment } from '../individual-assessment.model';
import { IndividualAssessmentFormService } from './individual-assessment-form.service';

import { IndividualAssessmentUpdateComponent } from './individual-assessment-update.component';

describe('IndividualAssessment Management Update Component', () => {
  let comp: IndividualAssessmentUpdateComponent;
  let fixture: ComponentFixture<IndividualAssessmentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let individualAssessmentFormService: IndividualAssessmentFormService;
  let individualAssessmentService: IndividualAssessmentService;
  let cBCREProcessService: CBCREProcessService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), IndividualAssessmentUpdateComponent],
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
      .overrideTemplate(IndividualAssessmentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(IndividualAssessmentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    individualAssessmentFormService = TestBed.inject(IndividualAssessmentFormService);
    individualAssessmentService = TestBed.inject(IndividualAssessmentService);
    cBCREProcessService = TestBed.inject(CBCREProcessService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call CBCREProcess query and add missing value', () => {
      const individualAssessment: IIndividualAssessment = { id: 456 };
      const cbcreprocess: ICBCREProcess = { id: 12937 };
      individualAssessment.cbcreprocess = cbcreprocess;

      const cBCREProcessCollection: ICBCREProcess[] = [{ id: 13248 }];
      jest.spyOn(cBCREProcessService, 'query').mockReturnValue(of(new HttpResponse({ body: cBCREProcessCollection })));
      const additionalCBCREProcesses = [cbcreprocess];
      const expectedCollection: ICBCREProcess[] = [...additionalCBCREProcesses, ...cBCREProcessCollection];
      jest.spyOn(cBCREProcessService, 'addCBCREProcessToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ individualAssessment });
      comp.ngOnInit();

      expect(cBCREProcessService.query).toHaveBeenCalled();
      expect(cBCREProcessService.addCBCREProcessToCollectionIfMissing).toHaveBeenCalledWith(
        cBCREProcessCollection,
        ...additionalCBCREProcesses.map(expect.objectContaining),
      );
      expect(comp.cBCREProcessesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const individualAssessment: IIndividualAssessment = { id: 456 };
      const cbcreprocess: ICBCREProcess = { id: 23823 };
      individualAssessment.cbcreprocess = cbcreprocess;

      activatedRoute.data = of({ individualAssessment });
      comp.ngOnInit();

      expect(comp.cBCREProcessesSharedCollection).toContain(cbcreprocess);
      expect(comp.individualAssessment).toEqual(individualAssessment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIndividualAssessment>>();
      const individualAssessment = { id: 123 };
      jest.spyOn(individualAssessmentFormService, 'getIndividualAssessment').mockReturnValue(individualAssessment);
      jest.spyOn(individualAssessmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ individualAssessment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: individualAssessment }));
      saveSubject.complete();

      // THEN
      expect(individualAssessmentFormService.getIndividualAssessment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(individualAssessmentService.update).toHaveBeenCalledWith(expect.objectContaining(individualAssessment));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIndividualAssessment>>();
      const individualAssessment = { id: 123 };
      jest.spyOn(individualAssessmentFormService, 'getIndividualAssessment').mockReturnValue({ id: null });
      jest.spyOn(individualAssessmentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ individualAssessment: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: individualAssessment }));
      saveSubject.complete();

      // THEN
      expect(individualAssessmentFormService.getIndividualAssessment).toHaveBeenCalled();
      expect(individualAssessmentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIndividualAssessment>>();
      const individualAssessment = { id: 123 };
      jest.spyOn(individualAssessmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ individualAssessment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(individualAssessmentService.update).toHaveBeenCalled();
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
  });
});

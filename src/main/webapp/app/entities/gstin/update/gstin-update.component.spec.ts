import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IOrganization } from 'app/entities/organization/organization.model';
import { OrganizationService } from 'app/entities/organization/service/organization.service';
import { GstinService } from '../service/gstin.service';
import { IGstin } from '../gstin.model';
import { GstinFormService } from './gstin-form.service';

import { GstinUpdateComponent } from './gstin-update.component';

describe('Gstin Management Update Component', () => {
  let comp: GstinUpdateComponent;
  let fixture: ComponentFixture<GstinUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let gstinFormService: GstinFormService;
  let gstinService: GstinService;
  let organizationService: OrganizationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), GstinUpdateComponent],
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
      .overrideTemplate(GstinUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GstinUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    gstinFormService = TestBed.inject(GstinFormService);
    gstinService = TestBed.inject(GstinService);
    organizationService = TestBed.inject(OrganizationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Organization query and add missing value', () => {
      const gstin: IGstin = { id: 456 };
      const organization: IOrganization = { id: 8290 };
      gstin.organization = organization;

      const organizationCollection: IOrganization[] = [{ id: 20000 }];
      jest.spyOn(organizationService, 'query').mockReturnValue(of(new HttpResponse({ body: organizationCollection })));
      const additionalOrganizations = [organization];
      const expectedCollection: IOrganization[] = [...additionalOrganizations, ...organizationCollection];
      jest.spyOn(organizationService, 'addOrganizationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ gstin });
      comp.ngOnInit();

      expect(organizationService.query).toHaveBeenCalled();
      expect(organizationService.addOrganizationToCollectionIfMissing).toHaveBeenCalledWith(
        organizationCollection,
        ...additionalOrganizations.map(expect.objectContaining),
      );
      expect(comp.organizationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const gstin: IGstin = { id: 456 };
      const organization: IOrganization = { id: 7299 };
      gstin.organization = organization;

      activatedRoute.data = of({ gstin });
      comp.ngOnInit();

      expect(comp.organizationsSharedCollection).toContain(organization);
      expect(comp.gstin).toEqual(gstin);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGstin>>();
      const gstin = { id: 123 };
      jest.spyOn(gstinFormService, 'getGstin').mockReturnValue(gstin);
      jest.spyOn(gstinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gstin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: gstin }));
      saveSubject.complete();

      // THEN
      expect(gstinFormService.getGstin).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(gstinService.update).toHaveBeenCalledWith(expect.objectContaining(gstin));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGstin>>();
      const gstin = { id: 123 };
      jest.spyOn(gstinFormService, 'getGstin').mockReturnValue({ id: null });
      jest.spyOn(gstinService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gstin: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: gstin }));
      saveSubject.complete();

      // THEN
      expect(gstinFormService.getGstin).toHaveBeenCalled();
      expect(gstinService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IGstin>>();
      const gstin = { id: 123 };
      jest.spyOn(gstinService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ gstin });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(gstinService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOrganization', () => {
      it('Should forward to organizationService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(organizationService, 'compareOrganization');
        comp.compareOrganization(entity, entity2);
        expect(organizationService.compareOrganization).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

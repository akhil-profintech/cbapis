import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ProspectRequestDetailComponent } from './prospect-request-detail.component';

describe('ProspectRequest Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProspectRequestDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ProspectRequestDetailComponent,
              resolve: { prospectRequest: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ProspectRequestDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load prospectRequest on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ProspectRequestDetailComponent);

      // THEN
      expect(instance.prospectRequest).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

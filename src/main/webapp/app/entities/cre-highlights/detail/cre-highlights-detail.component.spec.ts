import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { CREHighlightsDetailComponent } from './cre-highlights-detail.component';

describe('CREHighlights Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CREHighlightsDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CREHighlightsDetailComponent,
              resolve: { cREHighlights: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(CREHighlightsDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load cREHighlights on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CREHighlightsDetailComponent);

      // THEN
      expect(instance.cREHighlights).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
